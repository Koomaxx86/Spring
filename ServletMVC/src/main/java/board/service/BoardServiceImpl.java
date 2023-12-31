package board.service;

import java.util.List;


import board.dao.BoardDAO;
import board.dto.Board;

public class BoardServiceImpl implements BoardService {
	
	private BoardDAO boardDAO = new BoardDAO();

	@Override
	public List<Board> list() throws Exception {
		System.out.println("게시글 목록을 조회합니다.");
		
		// DAO를 통해 list를 조회 후 List타입으로 저장
		List<Board> boardList = boardDAO.list();
		
		// List의 크기를 확인해서 count 변수에 저장
		int count = boardList.size();
		System.out.println("boardList 요소 개수 : " + count);
		
		System.out.println("게시글 목록을 반환합니다.");
		return boardList;
	}

	@Override
	public Board select(int boardNo)  {
		Board board = boardDAO.select(boardNo);
//		String title = board.getTitle();
		return board;
	}

	@Override
	public int insert(Board board) throws Exception {
		int result = boardDAO.insert(board);
		return result;
	}

	@Override
	public int update(Board board) throws Exception {
		int result = boardDAO.update(board);
		return result;
	}

	@Override
	public int delete(int boardNo) throws Exception {
		int result = boardDAO.delete(boardNo);
		return result;
	}

}
