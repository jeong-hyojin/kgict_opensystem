package com.intern.study.board.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/board")
@CrossOrigin(origins = "*")
public class BoardController {

    @PostMapping("/write")
    public Map<String, Object> writePost(@RequestBody Map<String, String> body) {
        String title = body.get("title");
        String content = body.get("content");
        String password = body.get("password");

        String uuid = UUID.randomUUID().toString();

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", "게시글이 등록되었습니다");
        result.put("data", uuid);
        return result;
    }

    @PutMapping("/update")
    public Map<String, Object> updatePost(@RequestBody Map<String, String> body) {
        String uuid = body.get("uuid");
        String content = body.get("content");

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", "게시글이 수정되었습니다");
        result.put("data", uuid);
        return result;
    }

    @DeleteMapping("/delete")
    public Map<String, Object> deletePost(@RequestParam String uuid) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", "게시글이 삭제되었습니다");
        result.put("data", uuid);
        
        
        //result.put("fail", true);
        //result.put("message", "삭제에 실패했습니다.");
        //result.put("data", uuid);
        return result;
    }
}
