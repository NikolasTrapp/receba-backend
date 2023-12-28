package br.nikolastrapp.receba.services.impl;

import br.nikolastrapp.receba.repositories.FollowerRepository;
import br.nikolastrapp.receba.services.FollowerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FollowerServiceImpl implements FollowerService {

    private final FollowerRepository followerRepository;
}