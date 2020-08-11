package com.stfluffy.currencyconverter.dto;

import com.stfluffy.currencyconverter.role.Role;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private Set<Role> roles;

}
