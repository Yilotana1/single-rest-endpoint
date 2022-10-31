package com.example.demo.api;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    private final UserRepository userRepository;
    private final MockMvc mockMvc;

    @Autowired
    UserControllerIntegrationTest(UserRepository userRepository, MockMvc mockMvc) {
        this.userRepository = userRepository;
        this.mockMvc = mockMvc;
    }

    @Test
    void givenUserId_getUser_shouldReturnUserDTOAndOkStatus() throws Exception {
        //given
        var age = 15;
        var user = User.builder()
                .name("John")
                .lastName("Smith")
                .birthDate(LocalDate.ofYearDay(LocalDate.now().getYear() - age, 1))
                .build();
        userRepository.save(user);

        //then
        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.lastName").value(user.getLastName()))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    void givenBadUserId_getUser_shouldReturnNotFoundStatus() throws Exception {
        mockMvc.perform(get("/api/users/3"))
                .andExpect(status().isNotFound());
    }
}
