package br.nikolastrapp.receba.handler;

import br.nikolastrapp.receba.services.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/like")
public class LikeHandler {

    private final LikeService likeService;
}
