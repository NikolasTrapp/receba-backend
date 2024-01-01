package br.nikolastrapp.receba.services;

import br.nikolastrapp.receba.dtos.UserSignUpDTO;
import br.nikolastrapp.receba.entities.UserEntity;

import java.util.Set;

public interface UserService {

    UserSignUpDTO registerNewUser(UserSignUpDTO newUser);
    Set<UserEntity> findUsersByUsername(String username);
}