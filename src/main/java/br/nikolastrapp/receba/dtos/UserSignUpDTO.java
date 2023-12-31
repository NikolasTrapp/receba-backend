package br.nikolastrapp.receba.dtos;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@Builder
public class UserSignUpDTO {

    @NotBlank(message = "Username can't be empty")
    @Length(min = 5, message = "Username too short")
    private String username;

    @NotBlank(message = "Email can't be empty")
    private String email;

    @NotBlank(message = "Password can't be empty")
    @Length(min = 8, message = "Password too short")
    private String password;

    private UUID id;

}
