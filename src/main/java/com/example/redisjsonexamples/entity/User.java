package com.example.redisjsonexamples.entity;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class User {
    @Id
    @Indexed
    private String id;
    @Indexed
    private String name;
    @Indexed
    private Address address;
    private LocalDateTime insertTime;
    private LocalDateTime createTime;
}
