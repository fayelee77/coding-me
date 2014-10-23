package cn.youhui.manager;

import cn.youhui.common.Config;
import cn.youhui.log4ssy.api.Log4SSY;
import cn.youhui.log4ssy.utils.Enums.Debug;
import cn.youhui.log4ssy.utils.Enums.Event;
import cn.youhui.log4ssy.utils.Enums.Store;
import cn.youhui.log4ssy.utils.Enums.Type;
import cn.youhui.utils.NetManager;

public class LogManager {
	
	/**
	 * 记录访问日志
	 * @param dest
	 * @param destId
	 */
//	public static void addlog(String uid, String dest, String destId){
//		addlog(uid, "-1", "", dest, destId);
//	}

	/**
	 * 记录访问日志
	 * @param source
	 * @param sourceId
	 * @param dest
	 * @param destId
	 */
//	public static void addlog(String uid, String source, String sourceId, String dest, String destId){
//		addlog(uid, source, sourceId, dest, destId, "", "", "{}");
//	}
	
	/**
	 * 记录访问日志
	 * @param source       来源
	 * @param sourceId     来源ID
	 * @param dest         目的
	 * @param destId       目的ID
	 * @param pType        商品等
	 * @param pid          商品ID等
	 */
//	public static void addlog(String uid, String source, String sourceId, String dest, String destId, String pType, String pid, String message){
//		try {
//			String params = "uid=" + uid + "&source=" + source + "&sourceid=" + sourceId + "&dest=" + dest + 
//					"&destid=" + destId + "&ptype=" + pType + "&pid=" + pid + "&message=" + message;
//			NetManager.getInstance().send(Config.LOG_URL, params);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	
	
	public static void addlog(String uid, Event event, Type type, String id){		
		Log4SSY.Log(Config.debug, "SSYH", uid, event, type, Store.TAOBAO, id);
	}
	
	
	
	
	
	
	
	
	
	
}