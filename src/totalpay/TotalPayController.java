package totalpay;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projcet.ProjcetService;
import projcet.ProjcetServiceImpl;
import vo.RewardPay;

public class TotalPayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public TotalPayController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		TotalPayService service = new TotalPayServiceImpl();
		String type = request.getParameter("type");
		String forwardURL = "";
		ArrayList<RewardPay> totalpay = null;
		if(type.equals("totalpay")) {
			totalpay = service.rewardpay();
			request.setAttribute("totalpay", totalpay);
			forwardURL = "admin/payMng/totalpay.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
		dispatcher.forward(request, response);
	}

}
