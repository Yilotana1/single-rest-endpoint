package com.example.demo.service;

import com.example.demo.service.dto.UserServiceDTO;

import java.util.Optional;

public interface UserService {
    Optional<UserServiceDTO> findUserById(Long id);
}
