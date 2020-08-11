package com.stfluffy.currencyconverter.service;

import com.stfluffy.currencyconverter.dto.UserDto;
import com.stfluffy.currencyconverter.model.User;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    boolean addUser(User user);
}
