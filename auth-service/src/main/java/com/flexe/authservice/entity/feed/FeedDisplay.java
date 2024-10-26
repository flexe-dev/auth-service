package com.flexe.authservice.entity.feed;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class FeedDisplay {
    UserFeed userFeed;
    List<OriginReferenceLookup> recipientReferences;

    public FeedDisplay(){}

}
