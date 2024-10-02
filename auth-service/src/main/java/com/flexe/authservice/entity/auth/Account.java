package com.flexe.authservice.entity.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Getter
@Setter
@Document(collection = "Account")
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
}
