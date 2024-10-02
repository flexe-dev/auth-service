package com.flexe.authservice.entity.posts.text;

import com.flexe.authservice.entity.posts.media.MediaContent;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.List;

@Getter
@Setter
public class TextContent {
    @Id
    private String postId;
    @Field(targetType = FieldType.OBJECT_ID)
    private String userId;
    private String content;
    private List<MediaContent> media;
}


