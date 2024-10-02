package com.flexe.authservice.entity.posts.media;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class PostContent {
    private String id;
    private Map<String, Object> style;

    private Map<String, Object> options;

    private Map<String, Object> value;

    private ContentType type;

    public enum ContentType {
        TEXT,
        IMAGE,
        VIDEO,
        CAROUSEL,
    }

}
