package com.example.lv2brosecond.service;

import com.example.lv2brosecond.dto.UserLoginRequestDto;
import com.example.lv2brosecond.dto.UserSignupRequestDto;
import com.example.lv2brosecond.entity.User;
import com.example.lv2brosecond.repository.UserRepository;
import com.example.lv2brosecond.util.JwUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor//얘없이 주입?생성자만들고싶은데 왜 안되지?->주석처리 풀면 @RequiredArgsConstructor없으면 안됨
public class UserService {
    private final UserRepository userRepository;
//    public UserService(UserRepository userRepository){
//        this.userRepository = userRepository;
//    }
    private final JwUtil jwUtil;
//    public UserService(JwUtil jwUtil){
//        this.jwUtil = jwUtil;
//    }

    public String signup(UserSignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        //Optional null값을 허용함 id가 없을수도 있으니 Optional로 감싸준것 (래퍼타입)
        Optional<User> checkUsername = userRepository.findByUsername(username);
        // RequestDto -> Entity
        if(checkUsername.isPresent()){
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }
        User user = new User(username,password);//생성자만들때 인자를 2개 만들었으므로,인자자리에 username 하나만 넣어주면 에러난다.
        // DB 저장
        User SaveUser = userRepository.save(user);
        return requestDto.getUsername();
    }

    public String login(UserLoginRequestDto requestDto, HttpServletResponse httpServletResponse) {
        Optional<User> user = userRepository.findByUsername(requestDto.getUsername());
        if(user.get().getPassword().equals(requestDto.getPassword())){//비밀번호가 같다면 토큰을 생성해준다->헤더에 보내는데 2가지 방식이 있다.쿠키에 실어보내는 방식으로 해보자
            String token = jwUtil.createToken(user.get().getUsername(), user.get().getUserRole());
            jwUtil.addJwtToCookie(token, httpServletResponse);
        }else{
            throw new IllegalArgumentException("비밀번호가 틀렸습니다");
        }
        return requestDto.getUsername();

    }
}
