package com.flexe.authservice.entity.user;


import com.flexe.authservice.entity.relationships.UserInteractionRelationship;
import java.util.List;

public class UserNetwork extends UserDetails {
    private List<UserInteractionRelationship> following;
    private List<UserInteractionRelationship> followers;

    public UserNetwork() {
    }

    public UserNetwork(UserNode node){
        super(node);
        this.following = node.getFollowing();
        this.followers = node.getFollowers();
    }

    public List<UserInteractionRelationship> getFollowing() {
        return following;
    }

    public List<UserInteractionRelationship> getFollowers() {
        return followers;
    }

    public void setFollowing(List<UserInteractionRelationship> following) {
        this.following = following;
    }

    public void setFollowers(List<UserInteractionRelationship> followers) {
        this.followers = followers;
    }
}
