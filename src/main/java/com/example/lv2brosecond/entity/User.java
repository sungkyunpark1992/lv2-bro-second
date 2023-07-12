package com.example.lv2brosecond.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum userRole;//이 변수는 왜 필요한거였지? 어떤 역할을 하는거였지????

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    public User(){}

}
