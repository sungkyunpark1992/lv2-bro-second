package com.example.lv2brosecond.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private LocalDateTime createAt;

    @ManyToOne
    private User user;

    public Post(String title, String content, LocalDateTime createAt){
        this.title = title;
        this.content = content;
        this.createAt = createAt;
        this.user = user;
    }

    public Post(){}

}
