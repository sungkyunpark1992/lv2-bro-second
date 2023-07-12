package com.example.lv2brosecond.service;

import com.example.lv2brosecond.dto.PostRequestDto;
import com.example.lv2brosecond.dto.PostResponseDto;
import com.example.lv2brosecond.entity.Post;
import com.example.lv2brosecond.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service//얘가 없으면 PostController에서 주입받을 수 없다. 그러면@Autowired는 왜 있어야 하나? 얘 없어도 주입 되던데?
public class PostService {
    private final PostRepository postRepository;
//    @Autowired 얘는 왜 있어야 하는거? 없어도 @Service만 있으면 PostController에서 주입 되는데?
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }
    public PostResponseDto createPost(PostRequestDto requestDto) {
//        String title = requestDto.getTitle();
//        String content = requestDto.getContent();
        // RequestDto -> Entity
        Post post = new Post(requestDto);
        // DB 저장
        Post savePost = postRepository.save(post);
        // Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(savePost);
        return postResponseDto;
    }

    public List<PostResponseDto> getPosts() {
        return postRepository.findAllByOrderByModifiedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    public PostResponseDto getPost(Long id, PostRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Post post = findPost(id, requestDto);
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return postResponseDto;
    }

    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Post post = findPost(id, requestDto);
        // post 내용 수정
        post.update(requestDto);
        // Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return postResponseDto;
    }

    public Boolean deletePost(Long id, PostRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Post post = findPost(id, requestDto);
        // memo 삭제
        postRepository.delete(post);
        return true;
    }

    private Post findPost(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("선택한 글은 존재하지 않습니다."));
        return post;
    }
}
