package cybersoft.javabackend.gamedoanso.servlet;
/*
 * Mục đích: Servlet xử lý việc đăng nhập và đăng kí email
 * Người tạo: Trần Kim Phú
 * Ngày tạo: 23/01/2022
 * Version: 1.0
 * */
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.javabackend.gamedoanso.model.Email;
import cybersoft.javabackend.gamedoanso.service.StoreService;
import cybersoft.javabackend.gamedoanso.util.JspConst;
import cybersoft.javabackend.gamedoanso.util.UrlConst;

@WebServlet(name = "emailServlet", urlPatterns = { UrlConst.EMAIL_LOGIN, UrlConst.EMAIL_REGISTER })
public class EmailServlet extends HttpServlet {

	private List<Email> emails;

	@Override
	public void init() throws ServletException {
		super.init();
		emails = StoreService.emails;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();

		switch (path) {
		case UrlConst.EMAIL_LOGIN:
			String message = "Hãy đăng kí nếu đây là lần đầu bạn vào chơi!";
			Cookie[] cookies = req.getCookies();
			String email = null;
			if (cookies != null) {
				Optional<Cookie> emailCookieOpt = Arrays.asList(cookies).stream()
						.filter(t -> t.getName().equals("email")).findFirst();
				if (emailCookieOpt.isPresent()) {
					email = emailCookieOpt.get().getValue();
				}
			}
			if (email != null) {
				req.setAttribute("lastEmail", email);
			}
			req.setAttribute("loginMessage", message);
			req.getRequestDispatcher(JspConst.EMAIL_LOGIN).forward(req, resp);
			break;

		case UrlConst.EMAIL_REGISTER:
			req.getRequestDispatcher(JspConst.EMAIL_REGISTER).forward(req, resp);
			break;

		default:
			resp.getWriter().append("Đi sai đường rồi!");
			break;
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		String email, ePassword, reEPassword, message;

		switch (path) {

		case UrlConst.EMAIL_LOGIN:
			email = req.getParameter("email");
			ePassword = req.getParameter("ePassword");
			Cookie emailCookie = new Cookie("email", email);
			resp.addCookie(emailCookie);
			Optional<Email> curEmailOpt = emails.stream().filter(t -> t.getEmail().equals(email))
					.filter(t -> t.getEPassword().equals(ePassword)).findFirst();
			if (curEmailOpt.isPresent()) {
				HttpSession session = req.getSession();
				session.setAttribute("email", curEmailOpt.get());
				resp.sendRedirect(req.getContextPath() + UrlConst.USER_INFORMATION);
			} else {
				message = "Sai email đăng nhập hoặc mật khẩu!";
				req.setAttribute("loginMessage", message);
				req.getRequestDispatcher(JspConst.EMAIL_LOGIN).forward(req, resp);
			}
			break;

		case UrlConst.EMAIL_REGISTER:
			email = req.getParameter("email");
			ePassword = req.getParameter("ePassword");
			reEPassword = req.getParameter("reEPassword");
			Optional<Email> existsEmailOpt = emails.stream().filter(t -> t.getEmail().equals(email)).findFirst();
			message = kiemTraDangKi(email, ePassword, reEPassword, existsEmailOpt);
			req.setAttribute("registerMessage", message);
			req.getRequestDispatcher(JspConst.EMAIL_REGISTER).forward(req, resp);
			break;

		default:
			resp.getWriter().append("Đi sai đường rồi!");
			break;
		}

	}

	private boolean kiemTraEmail(String email) {
		String word = "";
		String tenDichVu = "";
		String specialCharacters = "!#$%&'()*+,-/:;<=>?@[]^_`{|}~\\/";
		// chia lam 2 phan, phan dau la word, phan cuoi la @gmail.com
		int k = email.length() - 10; // lay phan cuoi @gmail.com
		if (k <= 0) { // phan cuoi khong du ki tu @gmail.com
			return false;
		}
		for (int i = k; i < email.length(); i++) { // lay phan cuoi @gmail.com
			tenDichVu += email.charAt(i);
		}
		if (!tenDichVu.equals("@gmail.com")) { // neu phan cuoi khong phai la @gmail.com
			return false;
		}
		for (int i = 0; i < k; i++) { // lay phan dau word
			word += email.charAt(i);
		}
		if (word.charAt(0) == '.' || word.charAt(word.length() - 1) == '.') { // ki tu dau tien hoac cuoi la dau .
			return false;
		}
		for (int i = 0; i < word.length(); i++) { // kiem tra ki tu dac biet tru dau .
			for (int j = 0; j < specialCharacters.length(); j++) {
				if (word.charAt(i) == specialCharacters.charAt(j)) {
					return false;
				}
			}
		}
		for (int i = 0; i < word.length(); i++) { // xet dau .
			if (word.charAt(i) == '.') {
				if (i == word.length() - 1) { // neu dau . ke ben @gmail.com
					return false;
				}
				if (word.charAt(++i) == '.') { // neu 2 dau . lien ke
					return false;
				}
			}
		}
		return true;
	}

	private String kiemTraDangKi(String email, String ePassword, String reEPassword, Optional<Email> existsEmailOpt) {
		if (existsEmailOpt.isPresent()) {
			return "Email đã tồn tại!";
		} else {
			if (!kiemTraEmail(email)) { // kiem tra duoi @gmail.com
				return "Vui lòng tạo email hợp lệ!(đuôi @gmail.com và không có kí tự đặc biệt) Ví dụ: abc.def@gmail.com";
			} else if (ePassword == null || "".equals(ePassword) || ePassword.trim().isEmpty()) {
				return "Không thể để trống mật khẩu!";
			} else if (!ePassword.equals(reEPassword)) { // kiem tra mat khau nhap lai
				return "Mật khẩu không khớp!";
			} else {
				Email newEmail = new Email(email, ePassword);
				emails.add(newEmail);
				return "Tạo thành công!";
			}
		}
	}
}
