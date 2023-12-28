package br.nikolastrapp.receba.services.impl;

import br.nikolastrapp.receba.repositories.CommentRepository;
import br.nikolastrapp.receba.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
}