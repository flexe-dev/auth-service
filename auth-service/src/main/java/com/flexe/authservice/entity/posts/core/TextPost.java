package com.flexe.authservice.entity.posts.core;

import com.flexe.authservice.entity.posts.text.TextContent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextPost extends Post {

        private TextContent textContent;

        public TextPost() {
        }

        public TextPost(Post post, TextContent textContent) {
                super(post.getId(), post.getAuxData(), post.getMetrics());
                this.textContent = textContent;
        }
}
