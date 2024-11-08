package com.flexe.authservice.controller.post;

import com.flexe.authservice.auth.SessionAuthenticationToken;
import com.flexe.authservice.entity.feed.FeedDisplay;
import com.flexe.authservice.entity.feed.FeedPost;
import com.flexe.authservice.entity.posts.UserPosts;
import com.flexe.authservice.entity.posts.core.Post;
import com.flexe.authservice.service.AuthService;
import com.flexe.authservice.service.HTTPService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@RestController
@RequestMapping("api/post")
public class PostController {

    @Autowired
    private HTTPService httpService;

    private WebClient client;
    private String serverPrefix;
    @Autowired
    private AuthService authService;

    @PostConstruct
    public void init(){
        this.client = httpService.generateWebClient(HTTPService.TargetServer.POST);
        this.serverPrefix = httpService.getUriPrefix(HTTPService.TargetController.POST);
    }

    @GetMapping("/p/user/{userId}")
    public ResponseEntity<UserPosts> getAllUserPosts(@PathVariable String userId){
        try{
            ResponseEntity<UserPosts> userPosts = httpService.get(client, serverPrefix + "/user/" + userId, UserPosts.class);
            return ResponseEntity.ok(userPosts.getBody());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/feed")
    public ResponseEntity<List<FeedPost<?>>> getPostsFromReferences(@RequestBody List<FeedDisplay> postReferences){
        try{
            SessionAuthenticationToken token = authService.fetchUserToken();

            if(token == null){
                return ResponseEntity.badRequest().build();
            }

            ResponseEntity<List<FeedPost<?>>> response = httpService.post(client,
                    serverPrefix + "/feed",
                    postReferences,
                    new ParameterizedTypeReference<>() {
                    });

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
