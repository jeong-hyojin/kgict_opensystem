package com.intern.study.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
public class BoardController {
	
	// 게시글 작성
	@PostMapping("/write")
	public String writeBoard(@RequestBody Map<String, String> request) {
		
		//게시글 DB에 저장했다고 가정

		//출력
		return  "게시글 작성 완료" 	+  
				"\nUUID : " 	+ request.get("UUID") + 
				"\ntitle : "  	+ request.get("title") + 
				"\ncontent :" 	+ request.get("content") + 
				"\npassword : " + request.get("password"); 
		
	}
	
	//게시글 수정
	@PatchMapping("/update")
	public String updateBoard(@RequestBody Map<String, String> request) {
		
		
		//DB에서 UUID에 해당하는 게시글 가져왔다고 가정
		String oldUUID = request.get("oldUUID");
		
		//게시글 내용 수정
		Map<String, String> updateBoard = new HashMap<>();	
		updateBoard.put("UUID", request.get("newUUID"));
		updateBoard.put("content", request.get("content"));
		
    	//출력
		return  "게시글 수정 완료" 	+  
				"\nUUID : " 	+ updateBoard.get("UUID") + 
				"\ncontent : "  + updateBoard.get("content");	
	}
	
	
	//게시글 삭제
	@DeleteMapping("/delete")
	public String deleteBoard( @RequestParam String UUID) {
		
		//서버에서 UUID에 해당하는 게시글 가져왔다고 가정
		
		//게시글 삭제
		return  "게시글 " + UUID + " 이 삭제됐습니다.";
	}

}
