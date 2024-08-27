package com.flexe.authservice.repository;

import com.flexe.authservice.entity.auth.Session;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface SessionRepository extends MongoRepository<Session, String> {

    @Query("{ 'sessionToken' : ?0 }")
    public Optional<Session> findSessionBySessionToken(String sessionToken);
}
