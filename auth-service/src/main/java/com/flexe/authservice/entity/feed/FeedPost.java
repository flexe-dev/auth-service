package com.flexe.authservice.entity.feed;

import com.flexe.authservice.enums.PostEnums;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedPost<T> extends FeedDisplay{
    public PostEnums.PostType postType;
    public T post;
    public FeedUsers users;

    public FeedPost() {
    }
}
