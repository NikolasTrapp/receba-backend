package br.nikolastrapp.receba.services.impl;

import br.nikolastrapp.receba.entities.UserEntity;
import br.nikolastrapp.receba.repositories.UserRepository;
import br.nikolastrapp.receba.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

}