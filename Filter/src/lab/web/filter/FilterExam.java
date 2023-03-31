package lab.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


@WebFilter("/Filter.do") // 서블릿에서 실행하면 먼저 낚아 챔.
public class FilterExam implements Filter {


    public FilterExam() {
    }



	public void destroy() {

	}



	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("요청 시 필터 실행 중");
		System.out.println(request.getAttribute("message"));
		chain.doFilter(request, response); //원래 가던주소로 그대로 넘김.(서블릿)
		System.out.println("응답 시 필터 실행 중");
		System.out.println(request.getAttribute("message"));
	}


	public void init(FilterConfig fConfig) throws ServletException {
	}

}
