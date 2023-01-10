package com.example.redisjsonexamples.repository;

import com.example.redisjsonexamples.entity.User;
import com.redis.om.spring.annotations.Query;
import com.redis.om.spring.repository.RedisDocumentRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends RedisDocumentRepository<User, String> {

    //void updatePassword(String password);

}
