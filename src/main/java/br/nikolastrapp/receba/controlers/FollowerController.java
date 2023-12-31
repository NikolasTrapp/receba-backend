package br.nikolastrapp.receba.controlers;

import br.nikolastrapp.receba.services.FollowerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/follower")
public class FollowerController {

    private final FollowerService followerService;
}
