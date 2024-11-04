package com.flexe.authservice.entity.user.Network;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.flexe.authservice.entity.relationships.InteractionRelationship;
import com.flexe.authservice.entity.user.UserNode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserConnection extends InteractionRelationship<UserNode> {

    public enum RelationType {
        FOLLOWER,
        FOLLOWING
    }

    private RelationType relationType;

    private List<UserNode> mutual = new ArrayList<>();

    @Override
    @JsonIgnoreProperties({"followers", "following", "blockedUsers", "likedPosts", "savedPosts", "sharedPosts", "repostedPosts", "viewedPosts", "userPosts"})
    public UserNode getRoot() {
        return super.getRoot();
    }

    @Override
    public int hashCode() {
        return getRoot().hashCode();
    }

    public UserConnection() {
    }
}
