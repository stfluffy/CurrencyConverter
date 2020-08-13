package com.stfluffy.currencyconverter.service;

import com.stfluffy.currencyconverter.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    boolean addUser(UserDto userDto);
}
