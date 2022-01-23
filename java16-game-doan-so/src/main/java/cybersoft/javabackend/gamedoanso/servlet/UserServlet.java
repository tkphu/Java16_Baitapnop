package cybersoft.javabackend.gamedoanso.servlet;
/*
 * Mục đích: Servlet hiển thị thông tin người chơi
 * Người tạo: Trần Kim Phú
 * Ngày tạo: 23/01/2022
 * Version: 1.0
 * */
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.javabackend.gamedoanso.model.Email;
import cybersoft.javabackend.gamedoanso.model.GameRecord;
import cybersoft.javabackend.gamedoanso.service.StoreService;
import cybersoft.javabackend.gamedoanso.util.JspConst;
import cybersoft.javabackend.gamedoanso.util.UrlConst;

@WebServlet(name = "userServlet", urlPatterns = UrlConst.USER_INFORMATION)
public class UserServlet extends HttpServlet {

	private List<GameRecord> records;

	@Override
	public void init() throws ServletException {
		super.init();
		records = StoreService.records;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Email email = (Email) session.getAttribute("email");
		List<GameRecord> userGame = records.stream()
				.filter(t -> t.getEmail().equals(email))
				.sorted((o1, o2) -> {
				return o1.getId() - o2.getId();
				})
				.collect(Collectors.toList());
		req.setAttribute("userInformation", userGame);
		req.getRequestDispatcher(JspConst.USER_INFOMATION).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.removeAttribute("email");
		resp.sendRedirect(req.getContextPath() + UrlConst.EMAIL_LOGIN);
	}
}
