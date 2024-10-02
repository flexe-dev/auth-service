package com.flexe.authservice.entity.posts.metrics;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Getter
@Setter
public class CommentReact {

    private String id;
    private String postId;
    private String commentId;
    private String userId;
    private ReactType reactType;

    public enum ReactType{
        LIKE,
        DISLIKE
    }

    public CommentReact(ReactType reactType, String userId, String commentId, String postId) {
        this.postId = postId;
        this.reactType = reactType;
        this.userId = userId;
        this.commentId = commentId;
    }

}



