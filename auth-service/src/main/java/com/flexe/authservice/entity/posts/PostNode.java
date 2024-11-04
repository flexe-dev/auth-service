package com.flexe.authservice.entity.posts;

import com.flexe.authservice.entity.posts.core.Post;
import com.flexe.authservice.enums.PostEnums.PostType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PostNode {
    //Identifiers
    private String postId;
    private String userId;
    private PostType type;
    private Date postDate;

    //Metadata
    private List<String> tags;
    
    public PostNode(){

    }

}