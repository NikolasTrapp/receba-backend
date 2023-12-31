package br.nikolastrapp.receba.services;

import br.nikolastrapp.receba.dtos.UserSignUpDTO;

public interface UserService {

    UserSignUpDTO registerNewUser(UserSignUpDTO newUser);
}