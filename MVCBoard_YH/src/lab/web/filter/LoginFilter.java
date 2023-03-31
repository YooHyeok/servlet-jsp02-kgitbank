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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter({"/Board.do","/board/*"})
public class LoginFilter implements Filter {


    public LoginFilter() {
    }

    public void init(FilterConfig fConfig) throws ServletException {
    	// TODO Auto-generated method stub
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest)request;
		HttpServletResponse hres = (HttpServletResponse)response;
		HttpSession session = hreq.getSession();
		String userid = (String)session.getAttribute("userid");
		if(userid == null) {
			if(request.getParameter("action").equals("contact_do")) {
				//contact_do : 다른작업에는 상관없으나 
				
			}else {
				hres.sendRedirect("/MVC/Login.jsp");
				return;
			}
		}
		chain.doFilter(request, response);
	}



}
