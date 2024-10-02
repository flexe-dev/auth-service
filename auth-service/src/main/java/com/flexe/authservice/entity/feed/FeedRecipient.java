package com.flexe.authservice.entity.feed;

import com.flexe.authservice.entity.user.UserDetails;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeedRecipient {

    @Getter
    public static enum RecipientType {
        //Posts from Direct Following Users
        NETWORK(0),
        //Posts From Network Interactions
        LIKE(1),
        REPOST(2),
        COMMENT(3),
        //Posts From Groups a User is a Member Of
        GROUP(4),
        //Pro Users Feature - Added To Friends of Friends of ... Feed as Suggested People To Follow
        SUGGESTED(5),
        //Advertiser Promoted
        PROMOTED(6),
        AUTHOR(7);

        private final int value;

        RecipientType(int value) {
            this.value = value;
        }

        public static RecipientType fromValue(int value) {
            for (RecipientType type : RecipientType.values()) {
                if (type.getValue() == value) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Invalid RecipientType value: " + value);
        }
    }

    private UserDetails user;

    private RecipientType recipientType;

    //Relevant Lookup ID to the Relevant Recipient Type (ie. Group ID, User ID, etc.)
    private String recipient_reference_id;

    public FeedRecipient(){

    }

}
