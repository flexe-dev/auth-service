package com.flexe.authservice.entity.auth;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;

@Document(collection = "Session")
public class Session {
    @Id
    private String id;
    private String sessionToken;
    @Field(targetType = FieldType.OBJECT_ID)
    private String userId;
    private Date expires;

    public Session() {
    }

    public Session(String sessionToken, String userId, Date expires) {
        this.sessionToken = sessionToken;
        this.userId = userId;
        this.expires = expires;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    public boolean isExpired(){
        return expires.before(new Date());
    }
}
