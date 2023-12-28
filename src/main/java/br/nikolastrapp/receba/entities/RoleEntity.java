package br.nikolastrapp.receba.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "roles", schema = "receba")
@AllArgsConstructor
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long roleId;

    private String authority;

    public RoleEntity() {
        super();
    }

    public RoleEntity(String authority) {
        this.authority = authority;
    }

}