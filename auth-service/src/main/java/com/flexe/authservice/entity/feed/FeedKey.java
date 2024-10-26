package com.flexe.authservice.entity.feed;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class FeedKey {
    private String userId;
    private String postId;

}