package com.intern.study.admin.controller;

import com.intern.study.admin.service.AdminService;
import com.intern.study.common.domain.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    // 전체 사용자 목록을 조회
    @GetMapping("/user-list")
    public ApiResponse<?> userList() {
        return adminService.getUserList();
    }

    // 특정 사용자 정보를 조회
    @GetMapping("/{userId}")
    public ApiResponse<?> userDetail(@PathVariable String userId) {
        return adminService.getUserDetail(userId);
    }
}
