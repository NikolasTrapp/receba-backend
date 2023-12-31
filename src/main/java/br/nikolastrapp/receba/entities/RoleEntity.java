package br.nikolastrapp.receba.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "roles", schema = "receba")
@AllArgsConstructor
public class RoleEntity implements Serializable {

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