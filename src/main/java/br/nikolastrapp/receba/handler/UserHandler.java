package br.nikolastrapp.receba.handler;

import br.nikolastrapp.receba.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserHandler {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok().body("Hello, World!");
    }
}
