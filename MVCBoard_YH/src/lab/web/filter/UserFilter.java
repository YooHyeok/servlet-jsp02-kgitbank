package lab.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@WebFilter("/Board.do")
public class UserFilter implements Filter {

	public UserFilter() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest)request;
		String action = hreq.getParameter("action");
		//action이라는 파라미터를 받아와서 action이 update와 delete인 작업을 봄.
		if(action.equals("update")||action.equals("delete")) {
			HttpSession session = hreq.getSession();
			String userid = (String)session.getAttribute("userid");
			if(hreq.getParameter("userid").equals(userid)) {//글을쓴사람과 세션의 각각의 아이디를 비교해서 판단
				chain.doFilter(request, response);
			}else {
				request.setAttribute("message", "본인 글이 아니면 수정 또는 삭제할 수 없습니다.");
				hreq.getRequestDispatcher("/error/error.jsp").forward(request, response);
			}
		}else {
			chain.doFilter(request, response);
		}
	}


}
