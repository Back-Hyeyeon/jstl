package mvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Controller2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// (properites) 르로포티스에 있는 값을 가져와서 map을 할려고 한다.
	private Map<String, Object> commandMap = new HashMap<String, Object>();

	// 생성자를 부르고 디폴트생성자 부름 init 부르기 doGet,doPost 만들고 디스트로이
	public Controller2() {
		super();

	}

	// 우리가 사용하는 모든 엑션 객체들이 다 만들어진다.
	public void init(ServletConfig config) throws ServletException {
		// web.xml propertyConfig init-param
		String props = config.getInitParameter("propertyConfig");
		Properties pr = new Properties();
		String path = config.getServletContext().getRealPath("/WEB-INF");
		FileInputStream fi = null;
		try {
			fi = new FileInputStream(new File(path, props));
			pr.load(fi);
		} catch (IOException e) {
			throw new ServletException(e);
		} finally {
			if (fi != null)
				try {
					fi.close();
				} catch (IOException ex) {
				}
		}
		Iterator<Object> keyIter = pr.keySet().iterator();
		while (keyIter.hasNext()) {
			String command = (String) keyIter.next();
			String className = pr.getProperty(command);
			try {
				Class commandClass = Class.forName(className);
				Object commandInstance = commandClass.newInstance();

				commandMap.put(command, commandInstance);
				System.out.println(commandMap.toString());
			} catch (ClassNotFoundException e) {
				throw new ServletException(e);
			} catch (InstantiationException e) {
				throw new ServletException(e);
			} catch (IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		requestPro(request, response);
	}

	protected void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청한 내용을 알아내야 한다.
		//request.getRequestURI = 패키지 명부터 뒤에 모두 찾는다.
		
		String command = request.getRequestURI();
		System.out.println(command);
		String path = request.getContextPath();
		System.out.println(path);
		//request.getContextPath() = 프로젝트만 들고옴.
		if(command.indexOf(request.getContextPath())==0) {
			command = command.substring(path.length());
		}
		//모든자식객체 == 부모인터페이스로 받을 수 있다. 중요
		CommandProcess com = (CommandProcess)commandMap.get(command);
		System.out.println(com.toString());
		String view = null;
		try {
			view = com.requestPro(request, response);
			System.out.println(view.toString());
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher =	request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	
}
