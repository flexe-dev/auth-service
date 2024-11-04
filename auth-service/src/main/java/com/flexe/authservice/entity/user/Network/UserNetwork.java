package com.flexe.authservice.entity.user.Network;

import com.flexe.authservice.entity.user.UserDetails;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserNetwork {

    private UserDetails user;
    private List<UserConnection> following;
    private List<UserConnection> followers;

    public UserNetwork() {
    }

    public UserNetwork(UserDetails user, List<UserConnection> network){
        this.user = user;
        this.followers = network.stream().filter(connection -> connection.getRelationType() == UserConnection.RelationType.FOLLOWER).toList();
        this.following = network.stream().filter(connection -> connection.getRelationType() == UserConnection.RelationType.FOLLOWING).toList();
    }

}
