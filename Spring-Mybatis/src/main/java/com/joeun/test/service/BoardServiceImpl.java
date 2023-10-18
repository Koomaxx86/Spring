package com.joeun.test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joeun.test.dto.Board;
import com.joeun.test.dto.Option;
import com.joeun.test.mapper.BoardMapper;

@Service			// 비즈니스 로직을 처리하는 서비스 클래스로 빈 등록
public class BoardServiceImpl implements BoardService {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public List<Board> list() throws Exception {
		List<Board> boardList = boardMapper.list(0, "");
		return boardList;
	}

	@Override
	public Board select(int boardNo) throws Exception  {
		Board board = boardMapper.select(boardNo);
		return board;
	}

	@Override
	public Integer insert(Board board) throws Exception {
		int result = boardMapper.insert(board);
		return result;
	}

	@Override
	public Integer update(Board board) throws Exception {
		// int result = boardMapper.update(board);
		
		// Map 선언
		// Map<String, String> map = new HashMap<String, String>();
//		map.put("boardNo", board.getBoardNo() + "");
//		map.put("title", board.getTitle());
//		map.put("writer", board.getWriter());
//		map.put("content", board.getContent());
		
		// Map 선언 및 초기화
//		Map<String, String> map = new HashMap<String, String>() {{
//			put("boardNo", board.getBoardNo() + "");
//			put("title", board.getTitle());
//			put("writer", board.getWriter());
//			put("content", board.getContent());
//		}};
		
		// Map 선언 및 초기화
		Map<String, String> map = Map.of( "boardNo", board.getBoardNo() + "",
											"title", board.getTitle(),
											"writer", board.getWriter(),
											"content", board.getContent());
									
		
		int result = boardMapper.update(map);
		return result;
	}

	@Override
	public Integer delete(int boardNo) throws Exception {
		int result = boardMapper.delete(boardNo);
		return result;
	}

	@Override
	public List<Board> list(Integer searchType, String keyword) throws Exception {
		searchType = searchType == null ? 0 : searchType;
		keyword = keyword == null ? "" : keyword;
		return boardMapper.list(searchType, keyword);
	}

	@Override
	public List<Board> list(Option option) throws Exception {
		return boardMapper.list(option);
	}

}









