package com.example.lv2brosecond.controller;

import com.example.lv2brosecond.dto.PostRequestDto;
import com.example.lv2brosecond.dto.PostResponseDto;
import com.example.lv2brosecond.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;
    public PostController(PostService postService){//생성자부분:값을 초기화시켜주는것;외부에있는 PostService를 가져와서 초기화시켜준다.
        this.postService = postService;//이부분은 무조건 이해하고있어야한다. ioc di부분은 필수!!!
    } //1주차때 ioc di 부분 강좌를 다시보면 된다. 강의에 자세하게 나와있다. - 자료에// 생성자를 통해 주입을 받고있다.
//    PostService postService = new PostService(); //직접 객체를 만드는것 우리가 만든거라 관리에대한 책임이 우리한테있다.

    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return postService.createPost(requestDto, httpServletRequest);
    }
    @GetMapping("/posts")
    public List<PostResponseDto> getPosts(){
        return postService.getPosts();
    }
    @GetMapping("/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        return postService.getPost(id, requestDto);
    }
    @PutMapping("/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto,
                                      HttpServletRequest httpServletRequest){
        return postService.updatePost(id, requestDto, httpServletRequest);
    }
    @DeleteMapping("/post/{id}")
    public Boolean deletePost(@PathVariable Long id, HttpServletRequest httpServletRequest){
        return postService.deletePost(id, httpServletRequest);
    }
}
