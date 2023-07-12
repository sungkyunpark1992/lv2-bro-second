package com.example.lv2brosecond.entity;

import com.example.lv2brosecond.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "blogpost")
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "content", nullable = false)
    private String content;
//    private LocalDateTime createAt;

    @Column(name = "username", nullable = false)
    private String username;

    @ManyToOne
    private User user;

    //    public  Post (PostRequestDto)
//    public Post(String title, String content, LocalDateTime createAt, User user){
//        this.title = title;
//        this.content = content;
//        this.createAt = createAt;
//        this.user = user;
    //    }
    public Post(PostRequestDto requestDto, String username) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.username = username;
//        this.createAt = requestDto.get
    }
    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public Post(){}
}
