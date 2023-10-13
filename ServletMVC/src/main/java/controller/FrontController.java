package controller;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.controller.BoardController;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public FrontController() {
	}

	// doGet() : GET 메소드 방식으로 요청이 왔을 때 실행되는 메소드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// jsp 응답하기
		String view = "index.jsp";
		boolean redirect = false;
		ModelView modelView = null;

		String requestURI = request.getRequestURI();
		String pathInfo = request.getPathInfo();
		StringBuffer requestURL = request.getRequestURL();
		System.out.println("요청 URL : " + requestURI);
		System.out.println("requestURL : " + requestURL.toString());
		System.out.println("pathInfo : " + pathInfo);

		if (requestURI.contains("/board")) {
			BoardController boardController = new BoardController();
			try {
				modelView = boardController.process(request);
				view = modelView.getView();
				redirect = modelView.isRedirect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 모델 등록
		createModel(modelView, request);
		if (redirect) {
			response.sendRedirect(request.getContextPath() + view);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}

	// doPost() : POST 메소드 방식으로 요청이 왔을 때 실행되는 메소드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 모델 등록 메소드
	 * @param modelView
	 * @param request
	 */
	public void createModel(ModelView modelView, HttpServletRequest request) {
		if (modelView == null)
			return;
		Map<String, Object> model = modelView.getModel();

		if (model == null)
			return;

		Set<String> keySet = model.keySet();
		for (String key : keySet) {
			Object value = model.get(key);
			request.setAttribute(key, value);
		}
	}
}
