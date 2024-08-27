package com.flexe.authservice.controller.post.comment;

import com.flexe.authservice.auth.SessionAuthenticationToken;
import com.flexe.authservice.entity.posts.PostNode;
import com.flexe.authservice.entity.posts.metrics.Comment;
import com.flexe.authservice.entity.posts.metrics.CommentNode;
import com.flexe.authservice.entity.posts.metrics.CommentReact;
import com.flexe.authservice.service.AuthService;
import com.flexe.authservice.service.HTTPService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/comment")
public class CommentController {

    @Autowired
    private HTTPService httpService;

    @Autowired
    private AuthService authService;

    private WebClient client;
    private String serverPrefix;

    @PostConstruct
    public void init(){
        this.client = httpService.generateWebClient(HTTPService.TargetServer.POST);
        this.serverPrefix = httpService.getUriPrefix(HTTPService.TargetController.COMMENT);
    }

    @GetMapping("/p/get/post/{postId}")
    public ResponseEntity<CommentNode[]> getPostComments(@PathVariable String postId){
        try{
            ResponseEntity<CommentNode[]> response = httpService.get(client,
                    serverPrefix + "/get/post/" + postId,
                    CommentNode[].class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/add/{postType}")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment, @PathVariable PostNode.PostType postType){
        try{
            ResponseEntity<Comment> response = httpService.post(client,
                    serverPrefix + "/add/" + postType, comment,
                    Comment.class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/comment/{postType}")
    public ResponseEntity<Integer> deleteCommentById(@RequestBody CommentNode comment, @PathVariable PostNode.PostType postType){
        try{
            ResponseEntity<Integer> response = httpService.delete(client,
                    serverPrefix + "/delete/comment/" + postType, comment,
                    Integer.class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/post/{postId}")
    public ResponseEntity<String> deleteAllPostComments(@PathVariable String postId){
        try{
            ResponseEntity<String> response = httpService.delete(client,
                    serverPrefix + "/delete/post/" + postId,
                    String.class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<Comment> editComment(@RequestBody Comment comment){
        try{
            ResponseEntity<Comment> response = httpService.put(client,
                    serverPrefix + "/edit", comment,
                    Comment.class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/like/{commentId}/{postId}/{reversal}")
    public ResponseEntity<String> likeComment(@PathVariable String commentId, @PathVariable String postId, @PathVariable boolean reversal){
        try{
            SessionAuthenticationToken token = authService.fetchUserToken();

            if(token == null){
                return ResponseEntity.badRequest().build();
            }

            ResponseEntity<String> response = httpService.post(client,
                    serverPrefix + "/like/" + commentId + "/" + token.getUserId() + "/" + postId + "/" + reversal,
                    String.class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/dislike/{commentId}/{postId}/{reversal}")
    public ResponseEntity<String> dislikeComment(@PathVariable String commentId, @PathVariable String postId, @PathVariable boolean reversal){
        try{
            SessionAuthenticationToken token = authService.fetchUserToken();
            if(token == null){
                return ResponseEntity.badRequest().build();
            }

            ResponseEntity<String> response = httpService.post(client,
                    serverPrefix + "/dislike/" + commentId + "/" + token.getUserId() + "/" + postId + "/" + reversal,
                    String.class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    public static class ReactionMap extends HashMap<String, CommentReact.ReactType> {};

    @GetMapping("/reaction/{commentId}")
    public ResponseEntity<Map<String, CommentReact.ReactType>> getUserReactionsFromPostComments(@PathVariable String commentId){
        try{
           SessionAuthenticationToken token = authService.fetchUserToken();
              if(token == null){
                return ResponseEntity.badRequest().build();
              }

            ResponseEntity<ReactionMap> response = httpService.get(client,
                    serverPrefix + "/reaction/" + commentId + "/" + token.getUserId(),
                    ReactionMap.class
            );
            return ResponseEntity.ok(response.getBody());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/reaction/remove/{commentId}")
    public ResponseEntity<String> removeCommentReaction(@PathVariable String commentId){
        try{
            SessionAuthenticationToken token = authService.fetchUserToken();
            if(token == null){
                return ResponseEntity.badRequest().build();
            }

            ResponseEntity<String> response = httpService.delete(client,
                    serverPrefix + "/reaction/remove/" + commentId + "/" + token.getUserId(),
                    String.class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/report/{commentId}")
    public ResponseEntity<String> reportComment(@PathVariable String commentId){
        try{
            ResponseEntity<String> response = httpService.post(client, serverPrefix + "/report/" + commentId, String.class);
            return ResponseEntity.ok(response.getBody());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
