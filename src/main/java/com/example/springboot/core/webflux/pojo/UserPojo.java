package com.example.springboot.core.webflux.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPojo {
    private long id;
    private String userName;
    private int sex = 1;
    private String note;
}
