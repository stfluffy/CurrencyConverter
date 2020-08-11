package com.stfluffy.currencyconverter.service.impl;

import com.stfluffy.currencyconverter.model.User;
import com.stfluffy.currencyconverter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
                    user.get().getPassword(), user.get().getRoles());
        }

        throw new UsernameNotFoundException("Invalid username or password.");
    }
}
