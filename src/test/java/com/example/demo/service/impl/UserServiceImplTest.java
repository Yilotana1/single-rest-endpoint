package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceImplTest {


    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void givenUserId_getUser_shouldReturnOptionalUserServiceDTO() {
        //given
        var id = 1L;
        var age = 10;
        var birthDate = LocalDate.ofYearDay(LocalDate.now().getYear() - age, 1);
        var user = User.builder()
                .id(id)
                .name("John")
                .lastName("Smith")
                .birthDate(birthDate)
                .build();

        //when
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        var userServiceDTO = userService.findUserById(1L).get();

        //then
        assertThat(userServiceDTO.getName()).isEqualTo(user.getName());
        assertThat(userServiceDTO.getLastName()).isEqualTo(user.getLastName());
        assertThat(userServiceDTO.getAge()).isEqualTo(age);
    }

    @Test
    void givenBadUserId_getUser_shouldReturnEmptyOptional() {
        //when
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        var userServiceDTO = userService.findUserById(1L);

        //then;
        assertThat(userServiceDTO.isEmpty()).isTrue();
    }
}