package com.flexe.authservice.entity.user;

import com.flexe.authservice.entity.posts.PostNode;
import com.flexe.authservice.entity.relationships.CreationRelationship;
import com.flexe.authservice.entity.relationships.InteractionRelationship;
import com.flexe.authservice.entity.relationships.ShareRelationship;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;

@Getter
@Setter
public class UserNode extends UserDetails {

    //Will Add More Metadata Later (i.e. Account Type (recruiter, dev, agency....)

    private HashSet<CreationRelationship<PostNode>> userPosts = new HashSet<>();
    private HashSet<InteractionRelationship<UserDetails>> following = new HashSet<>();
    private HashSet<InteractionRelationship<UserDetails>> followers = new HashSet<>();
    private HashSet<InteractionRelationship<UserDetails>> blockedUsers = new HashSet<>();
    private HashSet<InteractionRelationship<PostNode>> likedPosts = new HashSet<>();
    private HashSet<InteractionRelationship<PostNode>> savedPosts = new HashSet<>();
    private HashSet<ShareRelationship<PostNode>> sharedPosts = new HashSet<>();
    private HashSet<InteractionRelationship<PostNode>> repostedPosts = new HashSet<>();
    private HashSet<InteractionRelationship<PostNode>> viewedPosts = new HashSet<>();

    public UserNode(){
    }
}