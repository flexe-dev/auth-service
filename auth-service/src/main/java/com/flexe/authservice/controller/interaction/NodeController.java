package com.flexe.authservice.controller.interaction;

import com.flexe.authservice.auth.SessionAuthenticationToken;
import com.flexe.authservice.entity.user.Network.UserNetwork;
import com.flexe.authservice.entity.user.UserNode;
import com.flexe.authservice.service.AuthService;
import com.flexe.authservice.service.HTTPService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestController
@RequestMapping("api/node")
public class NodeController {

    @Autowired
    private HTTPService httpService;

    @Autowired
    private AuthService authService;

    private WebClient client;
    private String serverPrefix;

    @PostConstruct
    public void init(){
        this.client = httpService.generateWebClient(HTTPService.TargetServer.INTERACTION);
        this.serverPrefix = httpService.getUriPrefix(HTTPService.TargetController.NODE);
    }

    @GetMapping("/user")
    public ResponseEntity<UserNode> getUserNode(){
        try{
            SessionAuthenticationToken token = authService.fetchUserToken();

            if(token == null){
                return ResponseEntity.badRequest().build();
            }

            ResponseEntity<UserNode> response = httpService.get(client,
                    serverPrefix + "/user/" + token.getUserId(),
                    UserNode.class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (WebClientResponseException e){
            return ResponseEntity.status(e.getStatusCode()).build();
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/p/network/{username}")
    public ResponseEntity<UserNetwork> getUserNetwork(@PathVariable String username, @RequestParam(required = false) String userId){
        try{
            ResponseEntity<UserNetwork> response = httpService.get(client,
                    serverPrefix + "/user/network/" + username + "?userId=" + userId,
                    UserNetwork.class);
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
