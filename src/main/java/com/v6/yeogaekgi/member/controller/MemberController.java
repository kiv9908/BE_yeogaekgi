package com.v6.yeogaekgi.member.controller;

import com.v6.yeogaekgi.jwt.TokenProvider;
import com.v6.yeogaekgi.member.dto.LoginRequestDTO;
import com.v6.yeogaekgi.member.dto.MemberRequestDTO;

import com.v6.yeogaekgi.member.dto.MemberResponseDTO;
import com.v6.yeogaekgi.member.service.MemberService;
import com.v6.yeogaekgi.security.MemberDetailsImpl;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin(origins = {"*"})
@RestController
@RequiredArgsConstructor
@Log4j2
public class MemberController {
    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    @PostMapping("/members/signup")
    public ResponseEntity<String>signUp(@RequestBody MemberRequestDTO memberReqeustDto) {
        try {
            memberService.signUp(memberReqeustDto);
            return new ResponseEntity<>("회원가입 성공", HttpStatus.CREATED);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/members/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDto, HttpServletResponse response) {
        try {
            MemberResponseDTO memberResponseDTO = memberService.login(loginRequestDto);
            String accessToken = tokenProvider.createToken(memberResponseDTO.getEmail());
            String refreshToken = tokenProvider.createRefreshToken(memberResponseDTO.getEmail());

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("message", "로그인 성공");
            responseBody.put("accessToken", accessToken);
            responseBody.put("refreshToken", refreshToken);
            responseBody.put("user", memberResponseDTO);

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/members/detail")
    public ResponseEntity<?> detail(@RequestBody LoginRequestDTO loginRequestDto) {
        try {
            MemberResponseDTO memberResponseDTO = memberService.login(loginRequestDto);

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("user", memberResponseDTO);

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/members/logout")
    public ResponseEntity<?> logout(@AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        try {
            boolean result = memberService.logout(memberDetails);
//            log.info("logout result: " + result);
            return new ResponseEntity<>("Logout Success", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}