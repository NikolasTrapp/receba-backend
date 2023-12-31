package br.nikolastrapp.receba.repositories;

import br.nikolastrapp.receba.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Query("select u from users u where u.username = :username or u.email = :username ")
    Optional<UserEntity> findByEmailOrUsername(String username);

    boolean existsUserEntityByUsernameOrEmail(String username, String email);
}