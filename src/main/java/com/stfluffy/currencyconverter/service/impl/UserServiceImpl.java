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
    public boolean addUser(User user) {
        Optional<User> getUser = userRepo.findByUsername(user.getUsername());

        if (getUser.isPresent()) {
            return false;
        }

        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return true;
    }

    private UserDto convertToDTO(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}
