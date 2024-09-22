package com.flexe.authservice.entity.posts;

import com.flexe.authservice.entity.posts.media.MediaPost;
import com.flexe.authservice.entity.posts.text.TextPost;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPosts {
    private MediaPost[] mediaPosts;
    private TextPost[] textPosts;

    public UserPosts(){
        this.mediaPosts = new MediaPost[0];
        this.textPosts = new TextPost[0];
    }


}
