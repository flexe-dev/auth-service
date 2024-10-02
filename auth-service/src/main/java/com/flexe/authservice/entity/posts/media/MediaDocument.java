package com.flexe.authservice.entity.posts.media;

import com.flexe.authservice.enums.PostEnums.UserPostStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MediaDocument {

    private String postId;
    private String userId;
    private List<PostContent> document;
    private String title;
    private UserPostStatus postStatus;
    private String thumbnail;



    public MediaDocument() {
    }

}
