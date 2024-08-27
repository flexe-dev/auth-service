package com.flexe.authservice.entity.relationships;

import com.flexe.authservice.entity.posts.PostNode;
import com.flexe.authservice.entity.user.UserNode;

import java.util.Date;

public class PostShareRelationship {


    private Long id;
    private Date timeStamp;
    private UserNode receiver;
    private PostNode post;

    public PostShareRelationship(){

    }

    public PostShareRelationship(PostNode node, UserNode target){
        this.post = node;
        this.receiver = target;
        this.timeStamp = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public UserNode getReceiver() {
        return receiver;
    }

    public void setReceiver(UserNode receiver) {
        this.receiver = receiver;
    }

    public PostNode getPost() {
        return post;
    }

    public void setPost(PostNode post) {
        this.post = post;
    }
}

