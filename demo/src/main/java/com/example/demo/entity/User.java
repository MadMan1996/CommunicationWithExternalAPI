package com.example.demo.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
    @Id
    private long id;
    private String login;

    private String password;
    @Column(name = "full_name")
    private String fullname;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private String email;
    @Column(name = "birth_day")
    private Instant birthday;

    private Boolean active;
    @Column(name = "update_date")
    private Instant updateDate;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @Column(name = "phone_number")
    private String phoneNumber;

}
