package com.flexe.authservice.entity.auth;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Document(collection = "account")
public class Account {
    @Id
    private String id;
    @Field(targetType = FieldType.OBJECT_ID)
    private String userId;
    private AccountType type;
    private AccountProvider provider;
    private String providerAccountId;

    public Account() {
    }

    public Account(String userId){
        this.userId = userId;
        this.type = AccountType.credentials;
        this.provider = AccountProvider.credentials;
        this.providerAccountId = userId;
    }

    public enum AccountType{
        credentials,
        oauth
    }

    public enum AccountProvider{
        credentials,
        github,
        google
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public AccountProvider getProvider() {
        return provider;
    }

    public void setProvider(AccountProvider provider) {
        this.provider = provider;
    }

    public String getProviderAccountId() {
        return providerAccountId;
    }

    public void setProviderAccountId(String providerAccountId) {
        this.providerAccountId = providerAccountId;
    }

}
