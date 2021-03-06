package member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberService;

public class IdchkServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idValue = request.getParameter("id");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		MemberService service = new MemberServiceImpl();
	
		System.out.println(idValue);
		
		
		try {
			
	
			int result = service.idCheck1(idValue);	
			request.setAttribute("result", result);

			
		}catch(Exception e) {
			out.print(e.getMessage());
		}	
		
		RequestDispatcher rd;
		String forwardURL = "idcheckrslt.jsp";
		rd =request.getRequestDispatcher(forwardURL);
		rd.forward(request, response);

	}
}
