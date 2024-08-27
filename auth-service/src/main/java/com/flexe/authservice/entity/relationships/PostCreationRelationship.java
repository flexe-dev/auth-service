package com.flexe.authservice.entity.relationships;

import com.flexe.authservice.entity.posts.PostNode;

import java.util.Date;

public class PostCreationRelationship {

    private Long id;
    private Date createdAt;
    private PostNode post;

    public PostCreationRelationship(){
    }

    public PostCreationRelationship(PostNode postNode){
        this.post = postNode;
        this.createdAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public PostNode getPost() {
        return post;
    }

    public void setPost(PostNode post) {
        this.post = post;
    }
}