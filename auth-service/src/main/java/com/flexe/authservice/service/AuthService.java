package com.flexe.authservice.service;

import com.flexe.authservice.auth.SessionAuthenticationToken;
import com.flexe.authservice.entity.auth.Account;
import com.flexe.authservice.entity.auth.EmailCredential;
import com.flexe.authservice.entity.auth.UserAuthentication;
import com.flexe.authservice.entity.user.User;
import com.flexe.authservice.repository.AccountRepository;
import com.flexe.authservice.repository.UserAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AuthService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;

    @Autowired
    private HTTPService httpService;

    public SessionAuthenticationToken fetchUserToken(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication instanceof SessionAuthenticationToken){
            return (SessionAuthenticationToken) authentication;
        }
        else{
            return null;
        }
    }

    public void createUser(EmailCredential credentials){
        User user = new User(credentials.getEmail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserAuthentication auth = new UserAuthentication(user.getId(), encoder.encode(credentials.getPassword()));
        Account account = new Account(user.getId());

        //Save New Data to the Database

        //Send Request to User Service
        WebClient client = httpService.generateWebClient(HTTPService.TargetServer.USER);
        ResponseEntity<User> newUserResponse = httpService.post(client, "/user/create", user, User.class);
        if(newUserResponse == null) throw new IllegalArgumentException("User Creation Failed");

        userAuthenticationRepository.save(auth);
        accountRepository.save(account);
    }

    public User authoriseUser(EmailCredential credentials){
        WebClient client = httpService.generateWebClient(HTTPService.TargetServer.USER);
        ResponseEntity<User> userResponse = httpService.get(client, "/user/find/email/" + credentials.getEmail(), User.class);
        User user = userResponse.getBody();

        if(user == null) return null;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserAuthentication auth = userAuthenticationRepository.findByUserId(user.getId()).orElse(null);
        if(auth == null) return null;

        return encoder.matches(credentials.getPassword(), auth.getPassword()) ? user : null;
    }
}
