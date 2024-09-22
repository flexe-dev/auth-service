package com.flexe.authservice.entity.relationships;

import com.flexe.authservice.entity.posts.PostNode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PostInteractionRelationship {

    private Long id;
    private Date timestamp;
    private PostNode post;

    public PostInteractionRelationship(){}

}

