package com.example.redisjsonexamples.entity;

import com.redis.om.spring.annotations.Indexed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @Indexed
    private String detail;
    private LocalDateTime insertTime;
}
