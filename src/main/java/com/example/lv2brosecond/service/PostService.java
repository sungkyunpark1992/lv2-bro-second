package com.example.lv2brosecond.service;

import com.example.lv2brosecond.dto.PostRequestDto;
import com.example.lv2brosecond.dto.PostResponseDto;
import com.example.lv2brosecond.entity.Post;
import com.example.lv2brosecond.repository.PostRepository;
import com.example.lv2brosecond.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service//얘가 없으면 PostController에서 주입받을 수 없다. 그러면@Autowired는 왜 있어야 하나? 얘 없어도 주입 되던데?
public class PostService {
    private final PostRepository postRepository;
//    @Autowired 얘는 왜 있어야 하는거? 없어도 @Service만 있으면 PostController에서 주입 되는데?
    private final JwtUtil jwUtil;
    public PostService(PostRepository postRepository, JwtUtil jwUtil){
        this.postRepository = postRepository;
        this.jwUtil = jwUtil;
    }



    public PostResponseDto createPost(PostRequestDto requestDto, HttpServletRequest httpServletRequest) {
//        String title = requestDto.getTitle();
//        String content = requestDto.getContent();
        String token = jwUtil.substringToken(jwUtil.getTokenFromRequest(httpServletRequest));
        String username = jwUtil.getUserInfoFromToken(token).getSubject();//유저정보 가져온 상태
        // RequestDto -> Entity
        Post post = new Post(requestDto, username);
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
        Post post = findPost(id);
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return postResponseDto;
    }

    public PostResponseDto updatePost(Long id, PostRequestDto requestDto, HttpServletRequest httpServletRequest) {
        //로그인한 유저의 정보를 가져오기 브라우저에 쿠키가 있다 가져오자 토큰에 유저정보가 있다. ==? 게시글의 유저정보랑 같은지
        String token = jwUtil.substringToken(jwUtil.getTokenFromRequest(httpServletRequest));
        String username = jwUtil.getUserInfoFromToken(token).getSubject();//유저정보 가져온 상태

        // 해당 메모가 DB에 존재하는지 확인
        Post post = findPost(id);

        if(post.getUsername().equals(username)) {
            // post 내용 수정
            post.update(requestDto);
        }else{
            throw new IllegalArgumentException("id가 맞지 않습니다.");
        }//delete는 내가 작업해보자
        // Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return postResponseDto;
    }

    public Boolean deletePost(Long id, HttpServletRequest httpServletRequest) {
//로그인한 유저의 정보를 가져오기 브라우저에 쿠키가 있다 가져오자 토큰에 유저정보가 있다. ==? 게시글의 유저정보랑 같은지
        String token = jwUtil.substringToken(jwUtil.getTokenFromRequest(httpServletRequest));
        String username = jwUtil.getUserInfoFromToken(token).getSubject();//유저정보 가져온 상태/.getSubject()는 filter쪽을 보자

        // 해당 메모가 DB에 존재하는지 확인
        Post post = findPost(id);
        if(post.getUsername().equals(username)){
            // memo 삭제
            postRepository.delete(post);
        }else{
            throw new IllegalArgumentException("password가 맞지 않습니다.");
        }
        return true;
    }

    private Post findPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("선택한 글은 존재하지 않습니다."));
        return post;
    }
}
