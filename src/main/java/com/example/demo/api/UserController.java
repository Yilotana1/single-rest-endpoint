package com.example.demo.api;

import com.example.demo.api.dto.UserDTO;
import com.example.demo.service.UserService;
import com.example.demo.service.dto.UserServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id")  long id){
         var userDTO = userService.getUser(id).map(this::mapToUserDTO);
         return userDTO
                 .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                 .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    private UserDTO mapToUserDTO(UserServiceDTO userServiceDTO){
        var userDTO = new UserDTO();
        userDTO.setName(userServiceDTO.getName());
        userDTO.setLastName(userServiceDTO.getLastName());
        userDTO.setAge(userServiceDTO.getAge());
        return userDTO;
    }
}
