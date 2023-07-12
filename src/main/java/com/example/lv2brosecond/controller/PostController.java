package com.example.lv2brosecond.controller;

import com.example.lv2brosecond.dto.PostRequestDto;
import com.example.lv2brosecond.dto.PostResponseDto;
import com.example.lv2brosecond.service.PostService;
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
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
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
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        return postService.updatePost(id, requestDto);
    }
    @DeleteMapping("/post/{id}")
    public Boolean deletePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        return postService.deletePost(id, requestDto);
    }



}
