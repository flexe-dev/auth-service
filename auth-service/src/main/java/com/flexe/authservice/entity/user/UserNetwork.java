package com.flexe.authservice.entity.user;


import com.flexe.authservice.entity.relationships.UserInteractionRelationship;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserNetwork extends UserDetails {
    private List<UserInteractionRelationship> following;
    private List<UserInteractionRelationship> followers;

    public UserNetwork() {
    }

}
