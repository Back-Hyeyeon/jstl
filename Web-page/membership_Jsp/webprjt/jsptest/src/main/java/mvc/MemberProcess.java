package mvc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberProcess implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setAttribute("message", "MemberProcess 실행결과전달");
		return "/mvc/process.jsp";
	}
}