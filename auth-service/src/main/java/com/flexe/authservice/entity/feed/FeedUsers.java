package com.flexe.authservice.entity.feed;

import com.flexe.authservice.entity.user.User;
import com.flexe.authservice.entity.user.UserDetails;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FeedUsers {

    private UserDetails creator;
    private List<UserDetails> users;

    public FeedUsers() {
    }
}
