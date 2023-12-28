package br.nikolastrapp.receba.services.impl;

import br.nikolastrapp.receba.repositories.PostRepository;
import br.nikolastrapp.receba.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
}