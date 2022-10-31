package com.example.demo.api;

import com.example.demo.api.dto.UserDTO;
import com.example.demo.service.UserService;
import com.example.demo.service.dto.UserServiceDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(value = {UserController.class})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Test
    public void givenUserId_getUser_shouldReturnUserDTOAndOkResponse() throws Exception {
        //given
        var userDTO = UserDTO.builder()
                .name("John")
                .lastName("Smith")
                .age(35)
                .build();
        var userServiceDTO = UserServiceDTO.builder()
                .name("John")
                .lastName("Smith")
                .age(35)
                .build();

        //when
        when(userService.findUserById(anyLong())).thenReturn(Optional.of(userServiceDTO));

        //then
        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(userDTO.getName()))
                .andExpect(jsonPath("$.lastName").value(userDTO.getLastName()))
                .andExpect(jsonPath("$.age").value(userDTO.getAge()));
    }

    @Test
    public void givenBadUserId_getUser_shouldReturnNotFoundStatus() throws Exception {
        //when
        when(userService.findUserById(anyLong())).thenReturn(Optional.empty());

        //then
        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isNotFound());
    }
}