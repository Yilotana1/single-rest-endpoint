package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.service.dto.UserServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserServiceDTO> findUserById(Long id) {
        var user = userRepository.findById(id);
        return user.map(this::mapToDTO);
    }
    private UserServiceDTO mapToDTO(User user){
        var userDTO = new UserServiceDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setLastName(user.getLastName());
        int age = Period.between(user.getBirthDate(), LocalDate.now()).getYears();
        userDTO.setAge(age);
        return userDTO;
    }
}
