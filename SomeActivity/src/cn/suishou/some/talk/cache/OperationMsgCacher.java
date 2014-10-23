package cn.suishou.some.talk.cache;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.mysql.jdbc.TimeUtil;

import cn.suishou.some.manager.JedisListManager;
import cn.suishou.some.common.Constant;
import cn.suishou.some.talk.bean.Message;
import cn.suishou.some.talk.dao.MessageDao;
import cn.suishou.some.talk.dao.impl.MessageDaoImpl;

/**
 * 在redis中操作message对象
 * @author weifeng
 * 2014-09-17
 */
public class OperationMsgCacher {
	
	private static OperationMsgCacher instance = null;
	private MessageDao messageDao = new MessageDaoImpl();
	
	private OperationMsgCacher() {
	}

	public static OperationMsgCacher getInstance() {
		return instance == null ? (instance = new OperationMsgCacher()) : instance;
	}
	
	public void saveMsg(Message msg){
		JedisListManager jedisList = new JedisListManager(Constant.TALK_MSG);
		jedisList.add(new Gson().toJson(msg));
	}
	
	public List<Message> getMsgListByTimer(String lastTime){
		Timestamp stampTime = null;
		if (null == lastTime || "".equals(lastTime.trim())){
			Date rightNow = new Date();
			stampTime = new Timestamp(rightNow.getTime() - TimeUnit.SECONDS.toMillis(2));
		}else {
			
			stampTime = Timestamp.valueOf(lastTime);
		}
		
		List<Message> listMsg = new ArrayList<Message>();
		JedisListManager jedisList = new JedisListManager(Constant.TALK_MSG);
		List<String> list = jedisList.getAll();
		Gson gson = new Gson();
		for (String msg : list){
			Message entity = gson.fromJson(msg, Message.class);
			if (entity.getGenerateTime().after(stampTime)){
				if ((entity.getU_nick() == null) || ("".equals(entity.getU_nick())) || entity.getU_nick().equals("null")){
					entity.setU_nick("游客");
				}
				listMsg.add(entity);
			}
		}
		
		Collections.sort(listMsg, new Comparator<Message>() {

			@Override
			public int compare(Message o1, Message o2) {
				if (o1.getGenerateTime().after(o2.getGenerateTime())){
					return 1;
				}else if(o1.getGenerateTime().before(o2.getGenerateTime())){
					return -1;
				}else {
					return 0;
				}
			}
		});
		
		return listMsg;
	}
	
	public List<Message> getMsgList(){
		
		List<Message> listMsg = new ArrayList<Message>();
		JedisListManager jedisList = new JedisListManager(Constant.TALK_MSG);
		List<String> list = jedisList.getAll();
		Gson gson = new Gson();
		for (String msg : list){
			Message entity = gson.fromJson(msg, Message.class);
			if ((entity.getU_nick() == null) || ("".equals(entity.getU_nick())) || entity.getU_nick().equals("null")){
				entity.setU_nick("游客");
			}
			listMsg.add(entity);
			
		}
		
		Collections.sort(listMsg, new Comparator<Message>() {

			@Override
			public int compare(Message o1, Message o2) {
				if (o1.getGenerateTime().after(o2.getGenerateTime())){
					return 1;
				}else if(o1.getGenerateTime().before(o2.getGenerateTime())){
					return -1;
				}else {
					return 0;
				}
			}
		});
		
		return listMsg;
	}
	
	// mysql --> redis
	public void reloadM2R(){
		JedisListManager jedisList = new JedisListManager(Constant.TALK_MSG);
		jedisList.clean();
		List<Message> msgList = this.messageDao.getAllMessage();
		for (Message msg : msgList){
			jedisList.add(new Gson().toJson(msg));
		}
	}
	
	public void reloadR2M(){
		this.messageDao.reloadR2M();
	}
	
}