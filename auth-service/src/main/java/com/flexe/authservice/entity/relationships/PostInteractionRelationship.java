package com.flexe.authservice.entity.relationships;

import com.flexe.authservice.entity.posts.PostNode;

import java.util.Date;

public class PostInteractionRelationship {

    private Long id;
    private Date timestamp;
    private PostNode post;

    public PostInteractionRelationship(){}

    public PostInteractionRelationship(PostNode node){
        this.post = node;
        this.timestamp = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public PostNode getPost() {
        return post;
    }

    public void setPost(PostNode post) {
        this.post = post;
    }
}

