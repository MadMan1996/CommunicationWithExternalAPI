package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    private Long id;

    private String name;

}
