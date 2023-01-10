package com.example.redisjsonexamples;

import com.example.redisjsonexamples.entity.Address;
import com.example.redisjsonexamples.entity.User;
import com.example.redisjsonexamples.repository.UserRepository;
import com.redis.om.spring.metamodel.indexed.TextTagField;
import com.redis.om.spring.ops.json.JSONOperations;
import com.redislabs.modules.rejson.Path;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    JSONOperations<String> jsonOperations;

    @Test
    public void test() {
        User u = User.builder()
                .name("SUN")
                .address(Address.builder().detail("114")
                        .insertTime(LocalDateTime.now()).build())
                .build();
        userRepository.save(u);

    }
    @Test
    public void test2() throws NoSuchFieldException {
        User u =
                User.builder().id("01GH3XCWKYWC1ECSHFTMKYGJ0S").address(Address.builder().detail("555").build()).build();
        userRepository.updateField(u,
                new TextTagField<User, Address>(User.class.getDeclaredField("address"), true), u.getAddress());
    }

    @Test
    public void test3() {
        jsonOperations.set(User.class.getName() + ":" + "01GH41C8ZHAYP1KXPZE3RS98JB", "1111", Path.of("address" +
                ".detail"));
    }

}
