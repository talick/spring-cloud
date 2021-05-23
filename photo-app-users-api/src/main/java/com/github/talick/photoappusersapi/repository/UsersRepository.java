package com.github.talick.photoappusersapi.repository;

import com.github.talick.photoappusersapi.data.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
