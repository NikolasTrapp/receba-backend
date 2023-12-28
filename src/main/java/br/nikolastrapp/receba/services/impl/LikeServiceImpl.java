package br.nikolastrapp.receba.services.impl;

import br.nikolastrapp.receba.repositories.LikeRepository;
import br.nikolastrapp.receba.services.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
}