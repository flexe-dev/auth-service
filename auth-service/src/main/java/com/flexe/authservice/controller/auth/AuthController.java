package com.flexe.authservice.controller.auth;

import com.flexe.authservice.auth.SessionAuthenticationToken;
import com.flexe.authservice.entity.auth.EmailCredential;
import com.flexe.authservice.entity.user.User;
import com.flexe.authservice.service.AuthService;
import io.sentry.Sentry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/p/create")
    public ResponseEntity<String> createUser(@RequestBody EmailCredential credentials){
        try{
            authService.createUser(credentials);
            return ResponseEntity.ok("User created");
        }
        catch (Exception e){
            Sentry.captureException(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/p/authenticate")
    public ResponseEntity<User> authenticateUser(@RequestBody EmailCredential credentials){
        try{
            User user = authService.authoriseUser(credentials);
            if(user == null){
                throw new Exception("User not found");
            }
            return ResponseEntity.ok(user);
        }
        catch (Exception e){
            Sentry.captureException(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/p/health")
    public ResponseEntity<String> healthCheck(){
        return ResponseEntity.ok("Auth service is running");
    }

    @GetMapping("/check")
    public ResponseEntity<String> check(){

        SessionAuthenticationToken token = authService.fetchUserToken();

        if(token == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok("Session Token was Validated, User ID: " + token.getUserId());
    }
}
