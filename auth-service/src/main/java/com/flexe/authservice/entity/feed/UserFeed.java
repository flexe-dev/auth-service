package com.flexe.authservice.entity.feed;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFeed {

    private FeedKey key;
    private Boolean readStatus = false;
    private Integer postType;
    private String groupId;

    public UserFeed(){

    }

}
