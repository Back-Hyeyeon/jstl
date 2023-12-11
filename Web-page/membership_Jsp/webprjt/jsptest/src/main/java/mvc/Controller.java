package mvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections4.map.HashedMap;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //properites 의 값을 가져와서 Map에 저장할려고한다. 
	private Map<String,Object> commandMap = new HashedMap<String, Object>(); 
    
    public Controller() {
        super();
    }

	//우리가 사용되어질 모든 ****ACtion 객체가 만들어진다. 
	public void init(ServletConfig config) throws ServletException {
		//web.xml propertyConfig 해당되는 init-param
		String props = config.getInitParameter("propertyConfig");
		Properties pr = new Properties();
		String path = config.getServletContext().getRealPath("/WEB-INF");
		FileInputStream fi = null; 
		try {
			fi= new FileInputStream(new File(path, props));
			pr.load(fi); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		Iterator<Object> keyIterator = pr.keySet().iterator(); 
		while(keyIterator.hasNext()) {
			String command = (String)keyIterator.next();
			String className = pr.getProperty(command);
			
			try {
				Class commandClass = Class.forName(className);
				@SuppressWarnings("deprecation")
				Object commanInstance = commandClass.newInstance(); 
				commandMap.put(command, commanInstance);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	
	private void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청한 내용을 알아내야한다. http://localhost:8080/jsptest/mvcMem/member.di
		//    /mvc/message.di=mvc.MessageProcess
		String view = null; 
		String command = request.getRequestURI(); // jsptest/mvcMem/member.di
		String path = request.getContextPath();   // jsptest
		if(command.indexOf(request.getContextPath()) == 0) {
			command = command.substring(path.length()); 
		}
		//모든자식객체는 => 부모인터페이스로 받을수 있다.
		//public class MessageProcess implements CommandProcess {}
		//ActionFactory factory = ActionFactory.getInstance();
		//Action action = factory.getAction(cmd);
		//ActionForward af = action.execute(request, response);
		CommandProcess com  = (CommandProcess)commandMap.get(command);
		try {
			view = com.requestPro(request, response);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}