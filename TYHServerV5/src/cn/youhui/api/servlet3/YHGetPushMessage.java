package cn.youhui.api.servlet3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.Action;
import cn.youhui.bean.PushMessage;
import cn.youhui.bean.UserDevtoken;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.LogManager;
import cn.youhui.manager.PushMsgManager;
import cn.youhui.manager.UserDevtokenDAO;
import cn.youhui.ramdata.AndroidPushMessageCacher;
import cn.youhui.ramdata.IphoneDevTokenCacher;
import cn.youhui.utils.ActionChangeUtil;
import cn.youhui.utils.ParamSignUtil;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.VersionUtil;


@WebServlet("/tyh3/pushmsg")
public class YHGetPushMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public YHGetPushMessage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		try{
		String access = request.getParameter("access_token");
		String uid = request.getParameter("uid");
		String platform = request.getParameter("platform");
		String version_code = request.getParameter("version_code");
		String devtoken = request.getParameter("devtoken");
		String openudid = ParamUtil.getParameter(request, "openudid");
		String imsi = ParamUtil.getParameter(request, "imsi");
		String imei = ParamUtil.getParameter(request, "imei");
		String idfa = ParamUtil.getParameter(request, "idfa");
		String macMd5 = ParamUtil.getParameter(request, "mac_md5");
		String osVersion = ParamUtil.getParameter(request, "os_version");
		
		String version = request.getParameter("version");
		String lastgetTime = request.getParameter("lastgettime");
		
		if(uid == null){
			uid = "";
		}
		
		String result = "";
		if("ios".equals(platform) || "iphone".equalsIgnoreCase(platform) || "ipad".equalsIgnoreCase(platform)){
			if(devtoken != null && !"".equals(devtoken)){
				IphoneDevTokenCacher.getInstance().addDevToken(devtoken);
				if(VersionUtil.isHigher("3.4.0", version)){
					IphoneDevTokenCacher.getInstance().addDevTokenv2(devtoken);
				}
				if(uid != null && !"".equals(uid)){
					IphoneDevTokenCacher.getInstance().add(uid, devtoken);
					if(VersionUtil.isHigher("3.4.0", version)){
						IphoneDevTokenCacher.getInstance().addv2(uid, devtoken);
					}
				}
				UserDevtoken ud = new UserDevtoken(openudid, imei, imsi, idfa, macMd5, osVersion, version, uid, devtoken);
				UserDevtokenDAO.getInstance().addUserDevtoken(ud);
			}
			result = RespStatusBuilder.messageJson(ActionStatus.PARAMAS_ERROR ,"", "").toString();
		}else{
			if(lastgetTime == null) 
				lastgetTime = "0";
			if (access == null){
				access = "";
			}
			result = getJsonmsg(access, uid, version, lastgetTime, platform, version_code);
			if("106617084".equals(uid)){
				String ret = "0";
				if(result.indexOf("OK") > 0 && result.indexOf("1000")>0 && result.indexOf("messages") > 0){
					ret = "1000";
				}else if(result.indexOf("1009") > 0){
					ret = "1009";
				}
			//	LogManager.addlog(uid, "1000", System.currentTimeMillis() + "," + lastgetTime + "," + ret + "..." + ParamSignUtil.paramsToStrs(request.getParameterMap()));
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(result);
		}catch(Exception e){
			response.getWriter().print(RespStatusBuilder.messageJson(ActionStatus.PARAMAS_ERROR ,"", ""));
		}
	}
	
	private String getJsonmsg(String access, String uid, String version, String lastgetTime, String platform, String version_code){
		StringBuffer ret = new StringBuffer();
		long lasttime = Long.valueOf(lastgetTime);
		List<PushMessage> list = AndroidPushMessageCacher.getInstance().getMsgAll();
		List<PushMessage> mlist = new ArrayList<PushMessage>();
		for(PushMessage msg : list){
			// 低版本检查，并转换Action
			msg = checkLowVersionAction(msg, uid, platform, version_code);
			long now = new Date().getTime();
			if(("all".equals(msg.getClientVersion()) || version.equals(msg.getClientVersion())) && now > msg.getStarttime() && msg.getEndtime() > now && (msg.getMode()!=0 || (msg.getMode() == 0 && lasttime < msg.getStarttime())) ){
				adduid(msg, uid);
				ret.append(msg.toJsonNew());
				msg.setTitle(msg.getTitle() + "," + msg.getStarttime() + "," + lasttime);
				mlist.add(msg);
			}
		}
		Map<String, String> map = AndroidPushMessageCacher.getInstance().getMsgValue(uid);
		if(map != null && map.size() > 0){
			for(Map.Entry<String, String> m : map.entrySet()){
				PushMessage msg = AndroidPushMessageCacher.getInstance().getMsg(m.getKey());
				// 低版本检查，并转换Action
				msg = checkLowVersionAction(msg, uid, platform, version_code);
				
				if(("all".equals(msg.getClientVersion()) || version.equals(msg.getClientVersion())) && new Date().getTime() > msg.getStarttime() && (msg.getMode()!=0 || (msg.getMode() == 0 && lasttime < msg.getStarttime())) && msg.getEndtime() > new Date().getTime()){
					adduid(msg, uid);
					ret.append(msg.toJsonNew(m.getValue()));
				    mlist.add(msg);
				}
			}
		}
		
		if(ret == null || ret.length() == 0){
			return RespStatusBuilder.messageJson(ActionStatus.NO_RESULT ,access).toString();
		}
		PushMsgManager.getInstance().addPushState(uid, "android", mlist);
		StringBuffer retu = new StringBuffer().append("{\"time\":\"").append(System.currentTimeMillis()).append("\",\"messages\":[").append(ret.toString().substring(0, ret.length()-1)).append("]}");
		return RespStatusBuilder.messageJson(ActionStatus.NORMAL_RETURNED ,access, retu.toString()).toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * 为话费充值增加unid参数，便于充话费返集分宝
	 */
	private void adduid(PushMessage msg, String uid){
		if(msg != null && msg.getAction() != null && msg.getAction().getActionValue().indexOf("http:") > -1){
			if( msg.getAction().getActionValue().indexOf("?") > -1){
				msg.getAction().setActionValue(msg.getAction().getActionValue()+ "&unid=" + uid);
			}else{
				msg.getAction().setActionValue(msg.getAction().getActionValue()+ "?unid=" + uid);
			}
		}
	}
	
	private PushMessage checkLowVersionAction(PushMessage msg, String uid, String platform, String version_code){
		Action action = ActionChangeUtil.lowVersionItemAction(msg.getId(), uid, platform, "11", 
				msg.getAction().getActionType(), msg.getAction().getActionValue(), version_code);
		msg.setAction(action);
		return msg;
	}
	public static void main(String[] args) {
		System.out.println(new Date());
	}

}