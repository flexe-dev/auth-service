package com.flexe.authservice.controller.post.text;

import com.flexe.authservice.auth.SessionAuthenticationToken;
import com.flexe.authservice.entity.posts.core.TextPost;
import com.flexe.authservice.service.AuthService;
import com.flexe.authservice.service.HTTPService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestController
@RequestMapping("api/text")
public class TextPostController {

    @Autowired
    private HTTPService httpService;

    @Autowired
    private AuthService authService;

    private WebClient client;
    private String serverPrefix;

    @PostConstruct
    public void init() {
        this.client = httpService.generateWebClient(HTTPService.TargetServer.POST);
        this.serverPrefix = httpService.getUriPrefix(HTTPService.TargetController.TEXT);
    }

    @PostMapping("/upload")
    public ResponseEntity<TextPost> savePost(@RequestBody TextPost post) {
        return handlePostRequest("/upload", post, TextPost.class);
    }

    @GetMapping("/p/find/{id}")
    public ResponseEntity<TextPost> getUserPostFromID(@PathVariable String id) {
        return handleGetRequest("/find/" + id, TextPost.class);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable String postId) {
        return handleDeleteRequest("/delete/" + postId, String.class);
    }

    private <T> ResponseEntity<T> handlePostRequest(String uri, @NonNull Object body, Class<T> responseType) {
        try {
            ResponseEntity<T> response = httpService.post(client, serverPrefix + uri, body, responseType);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private <T> ResponseEntity<T> handlePostRequest(String uri, Class<T> responseType) {
        try{
            ResponseEntity<T> response = httpService.post(client, serverPrefix + uri, responseType);
            return ResponseEntity.ok(response.getBody());
        } catch (WebClientResponseException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private <T> ResponseEntity<T> handleAuthenticatedPostRequest(String uri, Class<T> responseType) {
        SessionAuthenticationToken token = authService.fetchUserToken();
        if (token == null) {
            return ResponseEntity.badRequest().build();
        }
        return handlePostRequest(uri + "/" + token.getUserId(),  responseType);
    }

    private <T> ResponseEntity<T> handleAuthenticatedPostRequest(String uri, Object body, Class<T> responseType) {
        SessionAuthenticationToken token = authService.fetchUserToken();
        if (token == null) {
            return ResponseEntity.badRequest().build();
        }
        return handlePostRequest(uri + "/" + token.getUserId(), body, responseType);
    }

    private <T> ResponseEntity<T> handleGetRequest(String uri, Class<T> responseType) {
        try {
            ResponseEntity<T> response = httpService.get(client, serverPrefix + uri, responseType);
            return ResponseEntity.ok(response.getBody());
        } catch (WebClientResponseException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private <T> ResponseEntity<T> handleDeleteRequest(String uri, Class<T> responseType) {
        try {
            ResponseEntity<T> response = httpService.delete(client, serverPrefix + uri, responseType);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
