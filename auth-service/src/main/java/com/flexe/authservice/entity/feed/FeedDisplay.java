package com.flexe.authservice.entity.feed;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class FeedDisplay {
    UserFeed userFeed;
    Map<FeedRecipient.RecipientType, List<PostFeedReference>> recipientReferences;

    public FeedDisplay(){}
}
