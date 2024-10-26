package com.flexe.authservice.entity.feed;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserFeed {

    private FeedKey key;
    private Boolean readStatus = false;
    private String groupId;
    private Integer postType;
    private String creatorId;

    public UserFeed(){

    }

}
