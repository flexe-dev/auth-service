package com.flexe.authservice.entity.posts.metrics;

import com.flexe.authservice.entity.user.UserDetails;
import com.flexe.authservice.entity.user.UserDisplay;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CommentNode {
    private Comment comment;
    private UserDetails user;
    private List<CommentNode> children;

    public CommentNode() {
        this.children = new ArrayList<>();
    }

}

