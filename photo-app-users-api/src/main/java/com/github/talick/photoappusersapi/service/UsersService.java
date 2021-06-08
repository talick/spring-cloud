package com.github.talick.photoappusersapi.service;

import com.github.talick.photoappusersapi.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {
    UserDto createUser(UserDto user);
    UserDto getUserDetailByEmail(String email);

    UserDto getUserByUsedId(String userId);
}
