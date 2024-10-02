package com.flexe.authservice.entity.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@Document(collection = "User")
public class User{
    @Id
    private String id;
    private String email;
    private Date emailVerified;
    private String username;
    private String name;
    private String image;
    private Boolean onboarded;

    @Value("${DEFAULT_USER_IMAGE_URL}")
    private String DEFAULT_IMAGE_URL;

    public User() {
    }

    public User(String email){
        this.email = email;
        this.onboarded = false;
        this.name = "";
        this.username = UUID.randomUUID().toString();
        this.image = DEFAULT_IMAGE_URL;
    }


    public User(String id, String email, Date emailVerified, String username, String name, String image, Boolean onboarded) {
        this.id = id;
        this.email = email;
        this.emailVerified = emailVerified;
        this.username = username;
        this.name = name;
        this.image = image;
        this.onboarded = onboarded;
    }

}