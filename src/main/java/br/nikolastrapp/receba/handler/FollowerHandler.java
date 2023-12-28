package br.nikolastrapp.receba.handler;

import br.nikolastrapp.receba.services.FollowerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/follower")
public class FollowerHandler {

    private final FollowerService followerService;
}
