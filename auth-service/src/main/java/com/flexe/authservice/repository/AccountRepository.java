package com.flexe.authservice.repository;

import com.flexe.authservice.entity.auth.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
}
