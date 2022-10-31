package com.example.demo.service.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserServiceDTO {
    private Long id;

    private String name;

    private String lastName;

    private int age;
}
