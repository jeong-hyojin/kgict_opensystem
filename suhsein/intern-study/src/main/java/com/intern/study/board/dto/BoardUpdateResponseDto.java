package com.intern.study.board.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BoardUpdateResponseDto {
	BoardResponseDto prev;
	BoardResponseDto updated;

}
