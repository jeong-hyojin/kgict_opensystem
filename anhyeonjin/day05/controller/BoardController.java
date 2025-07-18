package com.intern.study.board.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/board")
@CrossOrigin(origins = "*")
public class BoardController {

    /**
     * 게시글 작성
     * 
     * @param body 요청 본문 (title, content, password)
     * @return 작성 결과 (success, message, uuid)
     */
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

    /**
     * 게시글 수정
     * 
     * @param body 요청 본문 (uuid, content)
     * @return 수정 결과 (success, message, uuid)
     */
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

    /**
     * 게시글 삭제
     * 
     * @param uuid 삭제할 게시글의 ID
     * @return 삭제 결과 (success, message, uuid)
     */
    @DeleteMapping("/delete")
    public Map<String, Object> deletePost(@RequestParam String uuid) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", "게시글이 삭제되었습니다");
        result.put("data", uuid);

        return result;
    }
}
