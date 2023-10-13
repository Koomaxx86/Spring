package board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import board.dto.Board;
import board.service.BoardService;
import board.service.BoardServiceImpl;
import controller.ModelView;

// /board
public class BoardController {

	BoardService boardService = new BoardServiceImpl();

	public ModelView process(HttpServletRequest request) throws Exception {
		
		String view = "";
		String requestURI = request.getRequestURI();
		ModelView modelView = new ModelView();

		// 게시글 목록
		// 경로명 확인 후 조건 부합시 메소드 호출
		if (requestURI.contains("list.do")) {
			// 메소드 호출하며 modelView 객체 전달
			list(modelView);
		}
		if (requestURI.contains("insert.do")) {
			insert(modelView);
		}
		if (requestURI.contains("insertPro.do")) {
			insertPro(modelView, request);
		}
		if (requestURI.contains("update.do")) {
			update(modelView);
		}
		if (requestURI.contains("updatePro.do")) {
			updatePro(modelView, request);
		}
		if (requestURI.contains("read.do")) {
			read(modelView);
		}
		if (requestURI.contains("deletePro.do")) {
			deletePro(modelView, request);
		}
		return modelView;
	}

	// 게시글 목록
	private void list(ModelView modelView) throws Exception {
		
		// BoardServiceImpl -> BoardDAO를 통해 list메소드 호출 후 값 반환
		List<Board> boardList = boardService.list();

		// 모델
		// Map타입의 HashMap model 객체 생성
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("boardList", boardList);	// key값과 value값을 model에 입력

		// 뷰
		String view = "/board/list.jsp";	// jsp 경로를 view에 전달

		modelView.addModel(model);			// modelView에 model를 추가
		modelView.setView(view);			// modelView view 추가
	}

	// 게시글 조회
	private void read(ModelView modelView) {

		// 임의의 10번 글 조회 후 board에 저장
		Board board = boardService.select(10);

		// 모델
		Map<String, Object> model = new HashMap<String, Object>(); // 모델 생성
		model.put("board", board);	// 불러온 게시글을 model에 저장

		// 뷰
		String view = "/board/read.jsp";	// read.do의 jsp경로 저장

		modelView.addModel(model);
		modelView.setView(view);
	}

	// 게시글 등록
	private void insert(ModelView modelView) {
		String view = "/board/insert.jsp";

		modelView.setView(view);
	}

	// 게시글 등록 처리
	private void insertPro(ModelView modelView, HttpServletRequest request) throws Exception {
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		Board board = new Board(title, writer, content);
		int result = boardService.insert(board);

		// 모델
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("result", result);

		// 뷰
		String view = "/board/list.do";

		modelView.addModel(model);
		modelView.setView(view);

		// 리다이렉트
		modelView.setRedirect(true);
	}

	// 게시글 수정
	private void update(ModelView modelView) {
		Board board = boardService.select(10);

		// 모델
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("board", board);

		// 뷰
		String view = "/board/update.jsp";

		modelView.addModel(model);
		modelView.setView(view);

	}

	// 게시글 수정 처리
	private void updatePro(ModelView modelView, HttpServletRequest request) throws Exception {
		String boardNo = request.getParameter("boardNo");
		int no = boardNo == null ? 0 : Integer.parseInt(boardNo);
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		Board board = new Board(title, writer, content);
		board.setBoardNo(no);

		int result = boardService.update(board);

		// 모델
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("result", result);

		// 뷰
		String view = "/board/list.do";

		modelView.addModel(model);
		modelView.setView(view);

		// 리다이렉트
		modelView.setRedirect(true);
	}

	// 게시글 삭제 처리
	private void deletePro(ModelView modelView, HttpServletRequest request) throws Exception {
		String boardNo = request.getParameter("boardNo");
		int no = boardNo == null ? 0 : Integer.parseInt(boardNo);
		int result = boardService.delete(no);

		// 모델
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("result", result);

		// 뷰
		String view = "/board/list.do";

		modelView.addModel(model);
		modelView.setView(view);

		// 리다이렉트
		modelView.setRedirect(true);
	}
}
