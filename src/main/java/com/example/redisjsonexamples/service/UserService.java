package com.example.redisjsonexamples.service;

import com.example.redisjsonexamples.entity.User;
import com.redis.om.spring.ops.json.JSONOperations;
import com.redislabs.modules.rejson.Path;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final JSONOperations<String> jsonOperations;

    private final static String PRE = User.class.getName();

    public UserService(JSONOperations<String> jsonOperations) {
        this.jsonOperations = jsonOperations;
    }

    public void update(User user, String password) {
        jsonOperations.set(User.class.getName() + ":" + "01GH41C8ZHAYP1KXPZE3RS98JB", "BO", Path.of("name"));
    }
}
