package com.flexe.authservice.entity.relationships;

import com.flexe.authservice.entity.posts.PostNode;
import com.flexe.authservice.entity.user.UserNode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PostShareRelationship {

    private Long id;
    private Date timeStamp;
    private UserNode receiver;
    private PostNode post;

    public PostShareRelationship(){

    }

}

