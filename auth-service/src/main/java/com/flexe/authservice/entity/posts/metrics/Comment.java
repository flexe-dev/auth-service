package com.flexe.authservice.entity.posts.metrics;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;

@Getter
@Setter
public class Comment {
    private  String id;
    private String postId;
    private String userId;
    private String parentId;
    private String content;
    private Integer likes;
    private Integer dislikes;
    private Date dateCreated;
    private Date dateUpdated;

    public Comment() {
    }


}

