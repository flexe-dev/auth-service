package com.flexe.authservice.entity.user;

import com.flexe.authservice.entity.relationships.PostCreationRelationship;
import com.flexe.authservice.entity.relationships.PostInteractionRelationship;
import com.flexe.authservice.entity.relationships.PostShareRelationship;
import com.flexe.authservice.entity.relationships.UserInteractionRelationship;

import java.util.ArrayList;
import java.util.List;

public class UserNode extends UserDetails {


    //Will Add More Metadata Later (i.e. Account Type (recruiter, dev, agency....)

    private List<PostCreationRelationship> userPosts = new ArrayList<>();
    private List<UserInteractionRelationship> following = new ArrayList<>();
    private List<UserInteractionRelationship> followers = new ArrayList<>();
    private List<UserInteractionRelationship> blockedUsers = new ArrayList<>();
    private List<PostInteractionRelationship> likedPosts = new ArrayList<>();
    private List<PostInteractionRelationship> savedPosts = new ArrayList<>();
    private List<PostShareRelationship> sharedPosts = new ArrayList<>();

    //Constructors

    public UserNode(){
    }

    //Default Constructor
    public UserNode(UserDisplay user){
        super(user);
    }


    public List<PostCreationRelationship> getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(List<PostCreationRelationship> userPosts) {
        this.userPosts = userPosts;
    }

    public List<UserInteractionRelationship> getFollowing() {
        return following;
    }

    public void setFollowing(List<UserInteractionRelationship> following) {
        this.following = following;
    }

    public List<UserInteractionRelationship> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserInteractionRelationship> followers) {
        this.followers = followers;
    }

    public List<PostInteractionRelationship> getLikedPosts() {
        return likedPosts;
    }

    public void setLikedPosts(List<PostInteractionRelationship> likedPosts) {
        this.likedPosts = likedPosts;
    }

    public List<PostInteractionRelationship> getSavedPosts() {
        return savedPosts;
    }

    public void setSavedPosts(List<PostInteractionRelationship> savedPosts) {
        this.savedPosts = savedPosts;
    }

    public List<PostShareRelationship> getSharedPosts() {
        return sharedPosts;
    }

    public void setSharedPosts(List<PostShareRelationship> sharedPosts) {
        this.sharedPosts = sharedPosts;
    }

    public List<UserInteractionRelationship> getBlockedUsers() {
        return blockedUsers;
    }

    public void setBlockedUsers(List<UserInteractionRelationship> blockedUsers) {
        this.blockedUsers = blockedUsers;
    }



}