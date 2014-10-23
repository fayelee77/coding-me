package cn.youhui.api.huafei;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.utils.ParamUtil;

/**
 * 话费充值接口
 * @author lijun
 * @since 2014-8-29
 */
@WebServlet("/huafeicz")
public class HuafeiCZServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      public static String huafeiUrl="huafei8-8";//huafei
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String phoneNum = ParamUtil.getParameter(request, "phone_num", true);
			int num = ParamUtil.getParameterInt(request, "num");
			String uid = ParamUtil.getParameter(request, "uid", true);
			if(num <= 0){
				response.sendRedirect("./"+huafeiUrl+"/error.html");
				return;
			}
			HuafeiCZ hf = new HuafeiCZ(uid, phoneNum, num);
			if(HuafeiCZDAO.getInstance().add(hf)){
				response.sendRedirect("./"+huafeiUrl+"/confirm_num3.jsp?tid="+hf.getTradeId() + "&phone_num=" + phoneNum + "&num=" + num + "&need_jfb=" + ExchangeRule.getNeedJfbNum(num) + "&tyh_web_uid=" + uid );
			}else{
				response.sendRedirect("./"+huafeiUrl+"/error.html");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}