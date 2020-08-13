package com.stfluffy.currencyconverter.service.impl;

import com.stfluffy.currencyconverter.dto.UserDto;
import com.stfluffy.currencyconverter.model.User;
import com.stfluffy.currencyconverter.repository.UserRepository;
import com.stfluffy.currencyconverter.role.Role;
import com.stfluffy.currencyconverter.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    public List<UserDto> findAll() {
        return userRepo.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public boolean addUser(UserDto userDto) {
        Optional<User> getUser = userRepo.findByUsername(userDto.getUsername());

        if (getUser.isPresent()) {
            return false;
        }

        userDto.setRoles(Collections.singleton(Role.USER));
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepo.save(convertToModel(userDto));
        return true;
    }


    private User convertToModel(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    private UserDto convertToDTO(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}
