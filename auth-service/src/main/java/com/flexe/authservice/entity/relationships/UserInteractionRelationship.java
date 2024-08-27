package com.flexe.authservice.entity.relationships;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.flexe.authservice.entity.user.UserNode;

import java.util.Date;

public class UserInteractionRelationship {

    private Long id;
    private Date timestamp;
    @JsonIgnoreProperties({"userPosts", "following", "followers", "blockedUsers", "likedPosts", "savedPosts", "sharedPosts"})
    private UserNode user;

    public UserInteractionRelationship(){}

    public UserInteractionRelationship(UserNode user){
        this.user = user;
        this.timestamp = new Date();
    }

    public Long getId() {
        return id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public UserNode getUser() {
        return user;
    }




}

