package br.nikolastrapp.receba.handler;

import br.nikolastrapp.receba.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/comment")
public class CommentHandler {

    private final CommentService commentService;
}
