package com.intern.study.board.controller;

import java.awt.dnd.DropTargetAdapter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
public class BoardController {
	
	// 게시글 작성
	@PostMapping("/write")
	public Map<String, Object> writeBoard(@RequestBody Map<String, String> request) {
		
		Map<String, Object> response = new HashMap<>();

	    Map<String, String> data = new HashMap<>();
	    data.put("UUID", request.get("UUID"));
	    data.put("title", request.get("title"));
	    data.put("content", request.get("content"));
	    data.put("password", request.get("password"));

	    response.put("code", "SUCCESS");
	    response.put("message", "게시글 작성이 완료되었습니다. (UUID: " + request.get("UUID") + 
	    											  ", 제목: " + request.get("title") + 
	    											  ", 내용: " + request.get("content") + 
	    											  ", 비밀번호: " + request.get("password") + 
	    											  ")");
	    response.put("data", data);

	    return response;
	}
	
	//게시글 수정
	@PutMapping("/update")
	public Map<String, Object> updateBoard(@RequestBody Map<String, String> request) {
		
		Map<String, Object> response = new HashMap<>();
		
		//DB에서 UUID에 해당하는 게시글 객체를 가져왔다고 가정
		
		//게시글 내용 수정
	    Map<String, String> data = new HashMap<>();
	    data.put("UUID", request.get("newUUID"));
	    data.put("content", request.get("newContent"));

	    response.put("code", "SUCCESS");
	    response.put("message", "게시글이 수정되었습니다. (UUID: " + request.get("newUUID") +
	    											", 내용: " + request.get("newContent") + 
	    											")");
	    response.put("data", data);

	    return response;
	}
	
	
	//게시글 삭제
	@DeleteMapping("/delete")
	public Map<String, Object> deleteBoard( @RequestParam String UUID) {
		
		//서버에서 UUID에 해당하는 게시글 가져왔다고 가정
		
	    Map<String, Object> response = new HashMap<>();

	    response.put("code", "SUCCESS");
	    response.put("message", "게시글 " + UUID + "이 삭제되었습니다.");
	    response.put("data", null);
	    
	    return response;
	}

}
