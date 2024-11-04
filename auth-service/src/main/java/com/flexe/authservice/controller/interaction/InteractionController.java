package com.flexe.authservice.controller.interaction;

import com.flexe.authservice.auth.SessionAuthenticationToken;
import com.flexe.authservice.entity.interactions.PostInteraction;
import com.flexe.authservice.service.AuthService;
import com.flexe.authservice.service.HTTPService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("api/interaction")
public class InteractionController {

    @Autowired
    private HTTPService httpService;

    private WebClient client;
    private String serverPrefix;

    @Autowired
    private AuthService authService;

    @PostConstruct
    public void init(){
        this.client = httpService.generateWebClient(HTTPService.TargetServer.INTERACTION);
        this.serverPrefix = httpService.getUriPrefix(HTTPService.TargetController.INTERACTION);
    }

    @PostMapping("/like/{postId}")
    public ResponseEntity<String> likePost(@PathVariable String postId){
        try{
            SessionAuthenticationToken token = authService.fetchUserToken();

            if(token == null){
                return ResponseEntity.badRequest().build();
            }

            ResponseEntity<String> response = httpService.post(client, serverPrefix + "/like/" + postId + "/" + token.getUserId(), String.class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/remove/like/{postId}")
    public ResponseEntity<String> removeLike(@PathVariable String postId){
        try{
            SessionAuthenticationToken token = authService.fetchUserToken();

            if(token == null){
                return ResponseEntity.badRequest().build();
            }

            ResponseEntity<String> response = httpService.post(client, serverPrefix + "/remove/like/" + postId + "/" + token.getUserId(),  String.class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/favourite/{postId}")
    public ResponseEntity<String> favouritePost(@PathVariable String postId){
        try{
            SessionAuthenticationToken token = authService.fetchUserToken();

            if(token == null){
                return ResponseEntity.badRequest().build();
            }

            ResponseEntity<String> response = httpService.post(client, serverPrefix + "/favourite/" + postId + "/" + token.getUserId(),  String.class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/remove/favourite/{postId}")
    public ResponseEntity<String> removeFavourite(@PathVariable String postId){
        try{
            SessionAuthenticationToken token = authService.fetchUserToken();

            if(token == null){
                return ResponseEntity.badRequest().build();
            }

            ResponseEntity<String> response = httpService.post(client, serverPrefix + "/remove/favourite/" + postId + "/" + token.getUserId(),  String.class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/repost/{postId}")
    public ResponseEntity<String> repostPost(@PathVariable String postId){
        try{
            SessionAuthenticationToken token = authService.fetchUserToken();

            if(token == null){
                return ResponseEntity.badRequest().build();
            }

            ResponseEntity<String> response = httpService.post(client, serverPrefix + "/repost/" + postId + "/" + token.getUserId(),  String.class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/remove/repost/{postId}")
    public ResponseEntity<String> removeRepost(@PathVariable String postId){
        try{
            SessionAuthenticationToken token = authService.fetchUserToken();

            if(token == null){
                return ResponseEntity.badRequest().build();
            }

            ResponseEntity<String> response = httpService.post(client, serverPrefix + "/remove/repost/" + postId + "/" + token.getUserId(),  String.class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
