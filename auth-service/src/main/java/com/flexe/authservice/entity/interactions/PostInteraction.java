package com.flexe.authservice.entity.interactions;

import com.flexe.authservice.entity.posts.PostNode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Getter
@Setter
public class PostInteraction {

    @NonNull
    private PostNode post;
    @NonNull
    private String userId;
    @Nullable
    private String targetId;


    public PostInteraction() {
    }

    public PostInteraction(@NonNull PostNode post, @NonNull String userId, @Nullable String targetId) {
        this.post = post;
        this.userId = userId;
        this.targetId = targetId;
    }

    public PostInteraction(@NonNull PostNode post, @NonNull String userId) {
        this.post = post;
        this.userId = userId;
    }
}
