package com.flexe.authservice.entity.posts;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
public class UserInteraction {
    @NonNull
    private String userId;
    @NonNull
    private String targetId;

    public UserInteraction(@NonNull String userId, @NonNull String targetId) {
        this.userId = userId;
        this.targetId = targetId;
    }

}
