package com.example.lv2brosecond.controller;

import com.example.lv2brosecond.dto.ProductRequestDto;
import com.example.lv2brosecond.dto.UserLoginRequestDto;
import com.example.lv2brosecond.dto.UserSignupRequestDto;
import com.example.lv2brosecond.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
//    UserService userService = new UserService();//생성자 기본, 필드값들을 특정값넣어주면서 초기화를 시켜주는 역할 oop랑 클래스생성자를 좀더 공부해보면 좋겠다.

    //처음에 UserService클래스만 생성했을때 생기는 빨간지렁이 에러는 무슨 말인가? Could not autowire. No beans of 'UserService' type found.
    @PostMapping("/auth/signup")
    public String signup(@RequestBody UserSignupRequestDto requestDto){
        String username = userService.signup(requestDto);//생성자를 주입하기전까진 userService가 빨간불뜬다. 생성자 주입과 생성은 어떤 차이점이 있는걸까?
        return username + " Signup Success!";
    }
    @PostMapping("/auth/login")
    public String login(@RequestBody UserLoginRequestDto requestDto, HttpServletResponse httpServletResponse){
        String username = userService.login(requestDto, httpServletResponse);
        return username + " Login Success!";
    }

    @PostMapping("/validation")
    @ResponseBody
    public ProductRequestDto testValid(@RequestBody @Valid ProductRequestDto requestDto) {
        return requestDto;
    }

}
