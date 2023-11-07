package com.example.exteranlclient.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User{
    @Id
    long id;

    String login;
    @Column(name = "full_name")
    String fullName;
    @Column(name = "phone_number")
    String phoneNumber;
    String email;
    @Column(name = "birth_day")
    Instant birthDay;

    Boolean active;
    @Column(name = "update_date")
    Instant updateDate;

}
