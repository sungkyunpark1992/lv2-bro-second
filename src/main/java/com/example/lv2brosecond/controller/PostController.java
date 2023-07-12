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
    public PostController(PostService postService){
        this.postService = postService;
    }

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
