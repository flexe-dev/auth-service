package com.flexe.authservice.controller.feed;

import com.flexe.authservice.auth.SessionAuthenticationToken;
import com.flexe.authservice.entity.feed.FeedDisplay;
import com.flexe.authservice.service.AuthService;
import com.flexe.authservice.service.HTTPService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@RestController
@RequestMapping("api/feed")
public class FeedController {

    @Autowired
    private HTTPService httpService;

    @Autowired
    private AuthService authService;

    private WebClient client;
    private String serverPrefix;

    @PostConstruct
    public void init(){
        this.client = httpService.generateWebClient(HTTPService.TargetServer.FEED);
        this.serverPrefix = httpService.getUriPrefix(HTTPService.TargetController.FEED);
    }

    @GetMapping("/user")
    public ResponseEntity<FeedDisplay[]> GetUserFeed(){
        try{
            SessionAuthenticationToken token = authService.fetchUserToken();

            if(token == null){
                return ResponseEntity.badRequest().build();
            }

            ResponseEntity<FeedDisplay[]> response = httpService.get(client,
                    serverPrefix + "/" + token.getUserId(),
                    FeedDisplay[].class);
            return ResponseEntity.ok(response.getBody());

        }
        catch (WebClientResponseException e){
            return ResponseEntity.status(e.getStatusCode()).build();
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
