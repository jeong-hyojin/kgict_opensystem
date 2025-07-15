package com.intern.study.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intern.study.common.ApiResponse;

@RestController
@RequestMapping("/user")
public class BoardController {
	 @GetMapping("/signup-url")
    public String signupUrl(@RequestParam String username
    					  , @RequestParam String email
    					  , @RequestParam String password) {
	 	return "가입 요청 확인: " + username + " / " + email + " / " + password;
    }    
	
    @GetMapping("/signup-fetch")
    public Map<String, Object> signupFetch(@RequestParam String username
    						             , @RequestParam String email
    						             , @RequestParam String password) {
    	Map<String, Object> data = new HashMap<>();
	    data.put("아이디"  , username);
	    data.put("이메일"  , email);
	    data.put("비밀번호", password);

	    Map<String, Object> response = new HashMap<>();
	    // 성공/실패 테스트용
	    String code = username.equals("success") ? "SUCCESS" : "FAIL";
	    response.put("code"   , code);
	    response.put("data"   , data);

	    return response;
    }
    
    @GetMapping("/signup-fetch-res")
    public ApiResponse<Object> signupFetchRes(@RequestParam String username
								            , @RequestParam String email
								            , @RequestParam String password) {
        Map<String, Object> user = new HashMap<>();
        user.put("id"      , username);
        user.put("email"   , email);
        user.put("password", password);

        return new ApiResponse<>(
                 "SUCCESS"
               ,  user
            );
    }
}