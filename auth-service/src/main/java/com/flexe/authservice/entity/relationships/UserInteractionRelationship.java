package com.flexe.authservice.entity.relationships;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.flexe.authservice.entity.user.UserNode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserInteractionRelationship {

    private Long id;
    private Date timestamp;
    @JsonIgnoreProperties({"userPosts", "following", "followers", "blockedUsers", "likedPosts", "savedPosts", "sharedPosts"})
    private UserNode user;

    public UserInteractionRelationship(){}

}

