package cybersoft.javabackend.gamedoanso.servlet;
/*
 * Mục đích: Servlet xử lí logic game
 * Người tạo: Trần Kim Phú
 * Ngày tạo: 23/01/2022
 * Version: 1.0
 * */
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.javabackend.gamedoanso.model.Email;
import cybersoft.javabackend.gamedoanso.model.GameRecord;
import cybersoft.javabackend.gamedoanso.service.GameService;
import cybersoft.javabackend.gamedoanso.util.JspConst;
import cybersoft.javabackend.gamedoanso.util.UrlConst;

@WebServlet(name = "gameServlet", urlPatterns = { UrlConst.GAME_ROOT, UrlConst.GAME_PLAY })
public class GameServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4241740329722099042L;

	private GameService service;

	@Override
	public void init() throws ServletException {
		super.init();
		service = new GameService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String message;
		message = "Lần đầu tiên hãy nhập con số mà bạn cảm thấy may mắn!";
		Email curEmail = (Email) req.getSession().getAttribute("email");

		GameRecord record = service.loadGame(curEmail.getEmail(), curEmail.getEPassword());
		req.setAttribute("message", message);
		req.setAttribute("record", record);

		req.getRequestDispatcher(JspConst.GAME_PLAY).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		String message;
		int recordId, tryNumber;
		GameRecord record;

		switch (path) {
		case UrlConst.GAME_PLAY: // when user input a try
			recordId = Integer.parseInt(req.getParameter("recordId"));
			tryNumber = Integer.parseInt(req.getParameter("tryNumber"));

			record = service.playGame(recordId, tryNumber);

			if (record.getIsFinished()) {
				message = "Chúc mừng bạn đã đoán chính xác!";
			} else {
				message = getHint(record.getNumber(), tryNumber);
			}

			req.setAttribute("message", message);
			req.setAttribute("record", record);

			req.getRequestDispatcher(JspConst.GAME_PLAY).forward(req, resp);
			break;
		}

	}

	private String getHint(int number, int tryNumber) {
		if (number > tryNumber)
			return "Số vừa đoán nhỏ hơn kết quả!";
		return "Số vừa đoán lớn hơn kết quả!";
	}
}
