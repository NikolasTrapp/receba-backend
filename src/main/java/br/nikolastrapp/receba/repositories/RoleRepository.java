package br.nikolastrapp.receba.repositories;

import br.nikolastrapp.receba.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Set;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>  {

    RoleEntity findByAuthority(String authority);
    Set<RoleEntity> findAllByAuthorityIsIn(Collection<String> autorities);
}
