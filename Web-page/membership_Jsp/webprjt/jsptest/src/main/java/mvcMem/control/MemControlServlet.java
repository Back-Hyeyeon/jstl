package mvcMem.control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvcMem.action.Action;

import java.io.IOException;
import java.io.PrintWriter;

public class MemControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String cmd = request.getParameter("cmd");
		
		System.out.println(cmd);
		
		if (cmd != null) {
			// ActionFactory는 싱글톤이라서 new를 해서 객체 할 수 없다 밑의 방법으로 들고온다.
			
			ActionFactory factory = ActionFactory.getInstance();
			Action action = factory.getAction(cmd);
			// execute 추상메소드를 준다.작동은 부모가 아니다 자식의 (request, response)이 작동한다.
			// 그리고 ActionForward가 받는다. execute(request, response)이 인설트 업데이트 딜리트 다된다.
			ActionForward af = action.execute(request, response);
			// is는 불리언
			if (af.isRedirect() == true) {
				response.sendRedirect(af.getUrl());
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(af.getUrl());
				rd.forward(request, response);
			}
		} else {
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head><title>Error</title></head>");
			out.println("<body>");
			out.println("<h4>올바른 요청이 아닙니다!</h4>");
			out.println("<h4>http://localhost:8080/myWeb/mvcMem/member.mdo?cmd=요청키워드</h4>");
			out.println("</body>");
			out.println("</html>");
		}

	}

}
