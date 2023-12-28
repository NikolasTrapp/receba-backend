package br.nikolastrapp.receba.repositories;

import br.nikolastrapp.receba.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>  {

    RoleEntity findByAuthority(String authority);
}
