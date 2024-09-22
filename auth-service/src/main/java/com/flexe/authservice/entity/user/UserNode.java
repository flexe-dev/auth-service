package com.flexe.authservice.entity.user;

import com.flexe.authservice.entity.relationships.PostCreationRelationship;
import com.flexe.authservice.entity.relationships.PostInteractionRelationship;
import com.flexe.authservice.entity.relationships.PostShareRelationship;
import com.flexe.authservice.entity.relationships.UserInteractionRelationship;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserNode extends UserDetails {

    //Will Add More Metadata Later (i.e. Account Type (recruiter, dev, agency....)

    private List<PostCreationRelationship> userPosts = new ArrayList<>();
    private List<UserInteractionRelationship> following = new ArrayList<>();
    private List<UserInteractionRelationship> followers = new ArrayList<>();
    private List<UserInteractionRelationship> blockedUsers = new ArrayList<>();
    private List<PostInteractionRelationship> likedPosts = new ArrayList<>();
    private List<PostInteractionRelationship> savedPosts = new ArrayList<>();
    private List<PostInteractionRelationship> repostedPosts = new ArrayList<>();
    private List<PostShareRelationship> sharedPosts = new ArrayList<>();

    //Constructors

    public UserNode(){
    }
}