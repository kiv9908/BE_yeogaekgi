package com.v6.yeogaekgi.services.controller;

import com.v6.yeogaekgi.security.MemberDetailsImpl;
import com.v6.yeogaekgi.services.dto.ServicesLikeDTO;
import com.v6.yeogaekgi.services.service.ServicesLikeService;
import com.v6.yeogaekgi.services.service.Servicesservice;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/servicesLike")
@Log4j2
@RequiredArgsConstructor
public class ServicesLikeController {
    private final ServicesLikeService servicesLikeService;

    @GetMapping("/list")
    public ResponseEntity<List<ServicesLikeDTO>> userLikeList(@AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        return new ResponseEntity<>(servicesLikeService.findAllServiceLike(memberDetails.getMember().getId()), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{servicesLikeId}")
    public ResponseEntity<String> deleteUserLike(@PathVariable Long servicesLikeId) {
        try {
            servicesLikeService.deleteById(servicesLikeId);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            // 로그 기록
            log.error("Error deleting service like: " + e.getMessage());
            return new ResponseEntity<>("false", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
