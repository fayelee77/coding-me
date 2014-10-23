package cn.suishou.some.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.suishou.some.bean.User;
import cn.suishou.some.db.SQL;

public class UserDAO {

	private static UserDAO instance = null;
	
	private UserDAO(){}

	public static UserDAO getInstance(){
		if(instance == null){
			instance = new UserDAO();
		}
		return instance;
	}
	
	public String getTBNick(String uid){
		String taobaoNick = "";
		Connection conn = null;
		try{
			conn = SQL.getInstance().getConnection();
			String sql = "select taobao_nick from `youhui_v3`.`user` where `uid`=?;";
			PreparedStatement state = conn.prepareStatement(sql);
			state.setString(1, uid);
			ResultSet rs = state.executeQuery();
			if(rs.next()){
				taobaoNick = rs.getString("taobao_nick");
			}	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(null, conn);
		}
		return taobaoNick;
	}
	
	/**
	 * 获取用户信息
	 * @param uid
	 * @param conn
	 * @return
	 */
	public User getUser(String uid, Connection conn){
		User user = new User();
		user.setUid(uid);
		try{
			String sql = "select `imei`,`active_time` from `youhui_v3`.`user` where `uid`=?;";
			PreparedStatement state = conn.prepareStatement(sql);
			state.setString(1, uid);
			ResultSet rs = state.executeQuery();
			if(rs.next()){
				user.setActiveTime(rs.getLong("active_time"));
				user.setImei(rs.getString("imei"));
			}else{
				user = null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}
	
	public void saveUseWinInfo(String uid, String winType, String uAnswer){
		Connection conn = null;
		try {
			conn = SQL.getInstance().getConnection();
			String sql = "insert into youhui_talk.user_win_info(uid, win_type, answer) values(?,?,?);";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, uid);
			pre.setString(2, winType);
			pre.setString(3, uAnswer);
			pre.execute();
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			SQL.getInstance().release(null, conn);
		}
		
	}
	
}