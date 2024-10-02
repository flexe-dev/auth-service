package com.flexe.authservice.entity.posts.media;

import com.flexe.authservice.enums.PostEnums.PostContentType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MediaContent {
    private String id;
    private String location;
    private PostContentType format;
    private Integer width;
    private Integer height;
    private String alt;
    private Boolean uploaded;

    public MediaContent() {
    }


}