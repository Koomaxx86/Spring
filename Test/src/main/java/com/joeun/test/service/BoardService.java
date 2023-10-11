package com.joeun.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joeun.test.dao.BoardDAO;
import com.joeun.test.dto.Board;

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	public List<Board> list() {

		return boardDAO.list();
	}

}






