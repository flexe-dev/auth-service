package com.flexe.authservice.entity.relationships;

import com.flexe.authservice.entity.posts.PostNode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PostCreationRelationship {

    private Long id;
    private Date createdAt;
    private PostNode post;

    public PostCreationRelationship(){
    }

}