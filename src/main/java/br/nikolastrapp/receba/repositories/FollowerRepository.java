package br.nikolastrapp.receba.repositories;

import br.nikolastrapp.receba.entities.FollowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FollowerRepository extends JpaRepository<FollowerEntity, UUID> {
}