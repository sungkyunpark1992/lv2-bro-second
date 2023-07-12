package com.example.lv2brosecond.service;

import com.example.lv2brosecond.dto.UserSignupRequestDto;
import com.example.lv2brosecond.entity.User;
import com.example.lv2brosecond.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
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
}
