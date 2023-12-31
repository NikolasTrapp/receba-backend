package br.nikolastrapp.receba.services.impl;

import br.nikolastrapp.receba.dtos.UserSignUpDTO;
import br.nikolastrapp.receba.entities.RoleEntity;
import br.nikolastrapp.receba.entities.UserEntity;
import br.nikolastrapp.receba.exceptions.UserException;
import br.nikolastrapp.receba.redis.RedisService;
import br.nikolastrapp.receba.repositories.RoleRepository;
import br.nikolastrapp.receba.repositories.UserRepository;
import br.nikolastrapp.receba.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Set;
import java.util.stream.Collectors;

import static br.nikolastrapp.receba.utils.StringUtils.format;
import static java.util.Optional.ofNullable;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String USER_AUTHORITY = "USER";
    private static final String WEB_CLIENT_ID = "receba-frontend";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RedisService redisService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserSignUpDTO registerNewUser(UserSignUpDTO newUser) {
        log.info("Received sign up for user: '{}'", newUser.getUsername());
        if (userRepository.existsUserEntityByUsernameOrEmail(newUser.getUsername(), newUser.getEmail())) {
            throw new UserException(format("User already exists with the provided username: {} or email: {}",
                    newUser.getUsername(), newUser.getEmail()));
        }

        try {
            var userEntity = convertUserToUserEntity(newUser);
            log.info("Signing user '{}' up.", newUser.getUsername());
            var savedUser = userRepository.save(userEntity);
            newUser.setId(savedUser.getId());
            return newUser;
        } catch (Exception err) {
            log.error("Could not register user '{}'.", newUser.getUsername(), err);
            throw new UserException("Could not complete user sign up.", err);
        }
    }

    private UserEntity convertUserToUserEntity(UserSignUpDTO user) {
        var authority = getAuthority();
        var encodedPassword = passwordEncoder.encode(user.getPassword());

        return UserEntity.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(encodedPassword)
                .authorities(Set.of(authority))
                .build();
    }

    private RoleEntity getAuthority() {
        return ofNullable(redisService.get(USER_AUTHORITY))
                .orElseGet(() -> {
                    var authotiy = ofNullable(roleRepository.findByAuthority(USER_AUTHORITY))
                            .orElseThrow(EntityNotFoundException::new);
                    redisService.put(USER_AUTHORITY, authotiy, 60);
                    return authotiy;
                });
    }

}