package com.flexe.authservice.controller.user.account;


import com.flexe.authservice.auth.SessionAuthenticationToken;
import com.flexe.authservice.entity.user.User;
import com.flexe.authservice.entity.user.UserDisplay;
import com.flexe.authservice.service.AuthService;
import com.flexe.authservice.service.HTTPService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private HTTPService httpService;

    @Autowired
    private AuthService authService;

    private WebClient client;
    private String serverPrefix;

    @PostConstruct
    public void init(){
        this.client = httpService.generateWebClient(HTTPService.TargetServer.USER);
        this.serverPrefix = httpService.getUriPrefix(HTTPService.TargetController.USER);
    }



    @PostMapping("/onboard")
    public ResponseEntity<UserDisplay> createProfile(@RequestBody UserDisplay user){
        try{
            ResponseEntity<UserDisplay> userResponse = httpService.post(client, serverPrefix + "/onboard", user, UserDisplay.class);
            return ResponseEntity.ok(userResponse.getBody());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/follow/{targetId}")
    public ResponseEntity<String> followUser(@PathVariable String targetId){
        try{
            SessionAuthenticationToken token = authService.fetchUserToken();
            if(token == null){
                return ResponseEntity.badRequest().build();
            }

            ResponseEntity<String> response = httpService.post(client, serverPrefix + "/follow/" + token.getUserId() + "/" + targetId, String.class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/unfollow/{targetId}")
    public ResponseEntity<String> unfollowUser(@PathVariable String targetId){
        try{
            SessionAuthenticationToken token = authService.fetchUserToken();
            if(token == null){
                return ResponseEntity.badRequest().build();
            }

            ResponseEntity<String> response = httpService.post(client, serverPrefix + "/unfollow/" + token.getUserId() + "/" + targetId, String.class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/block/{targetId}")
    public ResponseEntity<String> blockUser(@PathVariable String targetId){
        try{
            SessionAuthenticationToken token = authService.fetchUserToken();
            if(token == null){
                return ResponseEntity.badRequest().build();
            }

            ResponseEntity<String> response = httpService.post(client,  serverPrefix + "/block/" + token.getUserId() + "/" + targetId, String.class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/p/find/id/{id}")
    public ResponseEntity<User> findUserById(@PathVariable String id){
        try{
            ResponseEntity<User> response = httpService.get(client, serverPrefix + "/find/id/" + id, User.class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (WebClientResponseException e){
            return ResponseEntity.status(e.getStatusCode()).build();
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/p/find/username/{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable String username){
        try{
            ResponseEntity<User> response = httpService.get(client, serverPrefix + "/find/username/" + username, User.class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (WebClientResponseException e){
            return ResponseEntity.status(e.getStatusCode()).build();
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/p/find/email/{email}")
    public ResponseEntity<User> findUserByEmail(@PathVariable String email){
        try{
            ResponseEntity<User> response = httpService.get(client, serverPrefix + "/find/email/" + email, User.class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (WebClientResponseException e){
            return ResponseEntity.status(e.getStatusCode()).build();
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/p/display/find/{userId}")
    public ResponseEntity<UserDisplay> findUserDisplayByUserId(@PathVariable String userId){
        try{
            ResponseEntity<UserDisplay> response = httpService.get(client, serverPrefix + "/display/find/" + userId, UserDisplay.class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (WebClientResponseException e){
            return ResponseEntity.status(e.getStatusCode()).build();
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/p/display/find/username/{username}")
    public ResponseEntity<UserDisplay> findUserDisplayByUsername(@PathVariable String username){
        try{
            ResponseEntity<UserDisplay> response = httpService.get(client, serverPrefix + "/display/find/username/" + username, UserDisplay.class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (WebClientResponseException e){
            return ResponseEntity.status(e.getStatusCode()).build();
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<UserDisplay> updateUser(@RequestBody UserDisplay user){
        try{
            ResponseEntity<UserDisplay> response = httpService.put(client, serverPrefix + "/update", user, UserDisplay.class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId){
        try{
            ResponseEntity<String> response = httpService.delete(client, serverPrefix + "/delete/" + userId, String.class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/p/health")
    public ResponseEntity<String> healthCheck(){
        try{
            ResponseEntity<String> response = httpService.get(client, serverPrefix + "/health", String.class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }


}
