package com.flexe.authservice.controller.post;

import com.flexe.authservice.entity.posts.UserPosts;
import com.flexe.authservice.service.HTTPService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("api/post")
public class PostController {

    @Autowired
    private HTTPService httpService;

    private WebClient client;
    private String serverPrefix;

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


}
