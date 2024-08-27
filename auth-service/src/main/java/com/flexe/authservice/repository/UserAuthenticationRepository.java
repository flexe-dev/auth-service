package com.flexe.authservice.repository;

import com.flexe.authservice.entity.auth.UserAuthentication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserAuthenticationRepository extends MongoRepository<UserAuthentication, String> {
    @Query("{ 'userId' : ?0 }")
    Optional<UserAuthentication> findByUserId(String userId);
}
