package cybersoft.javabackend.gamedoanso.filter;
/*
 * Mục đích: Kiểm tra và xử lý request trước khi vào servlet
 * Người tạo: Trần Kim Phú
 * Ngày tạo: 23/01/2022
 * Version: 1.0
 * */
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.javabackend.gamedoanso.util.UrlConst;

@WebFilter(filterName = "authFilter", urlPatterns = UrlConst.GLOBAL)
public class AuthFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		// code xử lý request trước khi vào servlet
		String path = req.getServletPath();
		if (!(path.startsWith(UrlConst.EMAIL_LOGIN) || (path.startsWith(UrlConst.EMAIL_REGISTER)))) {
			Object email = req.getSession().getAttribute("email");
			if (email == null) {
				resp.sendRedirect(req.getContextPath() + UrlConst.EMAIL_LOGIN);
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
		// code xử lý request trước khi vào servlet
	}
}
