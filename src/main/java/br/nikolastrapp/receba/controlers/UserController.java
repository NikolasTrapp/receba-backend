package br.nikolastrapp.receba.controlers;

import br.nikolastrapp.receba.dtos.UserSignUpDTO;
import br.nikolastrapp.receba.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/heatlh")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<UserSignUpDTO> registerUser(@RequestBody @Valid UserSignUpDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerNewUser(user));
    }
}
