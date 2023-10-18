package com.joeun.test.service;

import java.util.List;

import com.joeun.test.dto.Board;
import com.joeun.test.dto.Option;

public interface BoardService {

	// 게시글 목록
	public List<Board> list() throws Exception;
	
	// 게시글 조회
	public Board select(int boardNo) throws Exception;
	
	// 게시글 등록
	public Integer insert(Board board) throws Exception;
	
	// 게시글 수정
	public Integer update(Board board) throws Exception;
	
	// 게시글 삭제
	public Integer delete(int boardNo) throws Exception;

	// 검색 (파라미터2개)
	public List<Board> list(Integer searchType, String keyword) throws Exception;

	// 검색 (객체)
	public List<Board> list(Option option) throws Exception;
}








