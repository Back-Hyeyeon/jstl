package mvc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemProcess implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setAttribute("message", "Mem 맴매매매맴 입니다. 실행결과전달");
		return "/mvc/process.jsp";
	}
}