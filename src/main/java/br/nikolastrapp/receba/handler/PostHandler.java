package br.nikolastrapp.receba.handler;

import br.nikolastrapp.receba.services.PostService;
import br.nikolastrapp.receba.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/post")
public class PostHandler {

    private final PostService postService;
    ;
}
