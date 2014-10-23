package cn.youhui.api.servlet3;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import cn.youhui.bean.ItemBean;
import cn.youhui.bean.ItemMiddle;
import cn.youhui.bean.ItemShop;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.cacher.dao.GuessYouLikeCacher;
import cn.youhui.cacher.dao.Switch4JfbCacher;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.LikeItemManager;
import cn.youhui.manager.TaobaoManager;
import cn.youhui.ramdata.Tag2ItemCacher;
import cn.youhui.ramdata.TagItemCacher;
import cn.youhui.utils.BadParameterException;
import cn.youhui.utils.JfbRateUtil;
import cn.youhui.utils.NetManager;
import cn.youhui.utils.ParamUtil;
import cn.youhui.utils.RespStatusBuilder;

/**
 * @author jiangjun
 * @since 2014-09-26
 * 
 */
@WebServlet("/GuessYouLike")
public class GuessYouLike extends HttpServlet {
	public static DecimalFormat df = new DecimalFormat("#0.0");
	private static final long serialVersionUID = 1L;
	private static final int GUESS_YOU_LIKE_NUM = 100;        //猜你喜欢推荐商品数量
    public GuessYouLike() {
        super();
    }

    public static void main(String[] args) {
    	 System.out.println(df.format(Double.parseDouble("3.21")/Double.parseDouble("23.43")*Double.parseDouble("10")));
	}
    
    public static int numPerPage=10;//一页十个数据
    public static String add="http://b17.cn/skip/tyh3_getitemandshop";//获取商品详情 接口
    public static String urlPath="http://172.16.71.64:8080/suggest/getUserSuggest";//获取推荐 接口
    public static String layout_type="grid";//布局格式 网格
    private static final String favAdTagId = "607";        //收藏夹广告tagid
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		//双日列表 单日网格
		if(getDay()%2==0){
			layout_type="line";
		}
		
		try{
			String uid = ParamUtil.getParameter(request, "uid");
			String collectionIds=ParamUtil.getParameter(request, "collection_ids");
			String xx=collectionIds;
			int pageNow=ParamUtil.getParameterInt(request, "page",1);
			
			
			String data="";
			if(pageNow<=1){
				data=firstPage(collectionIds, xx, uid, pageNow);
			}else{
				data=otherPages(collectionIds, xx, uid, pageNow);
			}
			String jfbSwitch=Switch4JfbCacher.getInstance().getSwitch();
			response.getWriter().print(RespStatusBuilder.message4GuessYouLike(ActionStatus.NORMAL_RETURNED,GuessYouLikeCacher.getInstance().getTotalPages(uid, numPerPage),pageNow, data,layout_type,jfbSwitch).toString());
		}catch(BadParameterException e){
			e.printStackTrace();
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.PARAMAS_ERROR).toString());
		}
	}

	public String otherPages(String collectionIds,String xx,String uid,int pageNow){
		StringBuffer sb=new StringBuffer();
		String dat=GuessYouLikeCacher.getInstance().getSecondKillByUid(uid,pageNow, numPerPage);
		if("".equals(dat)){
			return firstPage(collectionIds, xx, uid, 1);
		}else{
			sb.append("<items>");
			for(int i=0;i<dat.split(",").length;i++){
				String itemid=dat.split(",")[i];
				TeJiaGoodsBean tb=TagItemCacher.getInstance().getProduct(itemid);
				if(!collectionIds.contains(itemid)){
					ItemBean item=getFromTejiaGoodsBean(tb);
					sb.append(item.toXML4GuessYouLike(0));
				}
			}
			sb.append("</items>");
			return sb.toString();
		}
	}
	
	public  String firstPage(String collectionIds,String xx,String uid,int pageNow){
		StringBuffer sb=new StringBuffer();
		sb.append("<items>");
		
		//广告
		List<TeJiaGoodsBean> itemlist = Tag2ItemCacher.getInstance().getItemsByTagid(favAdTagId, 1, 10);
		for(TeJiaGoodsBean tb:itemlist){
			ItemBean item=getFromTejiaGoodsBean(tb);
			sb.append(item.toXML4GuessYouLike(1));
		}
		
		//获取推荐商品id 并且把客户端传过来的收藏商品id 拼上去
		String content="uid="+uid+"&size="+(GUESS_YOU_LIKE_NUM);
		String bac=NetManager.getInstance().send(urlPath, content);
		JsonParser jp=new JsonParser();
		JsonArray ja=jp.parse(bac).getAsJsonArray();
		for(int i=0;i<ja.size();i++){
			String itemid=ja.get(i).getAsString();
			if(!collectionIds.contains(itemid)){
				if(!xx.equals("")){
					collectionIds=collectionIds+","+itemid;
				}else{
					collectionIds=collectionIds+itemid+",";
				}
			}
		}
		if(xx.equals("")&&collectionIds.length()>0){
			collectionIds=collectionIds.substring(0, collectionIds.length()-1);
		}
		
		GuessYouLikeCacher.getInstance().add(uid,collectionIds);
		
		String dat=GuessYouLikeCacher.getInstance().getSecondKillByUid(uid, pageNow, numPerPage);
		sb.append(getFromWeb( uid,dat));
		sb.append("</items>");
		return sb.toString();
	}
	
	
	public ItemBean getFromTejiaGoodsBean(TeJiaGoodsBean tb){
		ItemBean item=new ItemBean();
		item.setNum_iid(tb.getItem_id());
		item.setPrice_low(tb.getPrice_low());
		item.setPrice_high(tb.getPrice_high());
		item.setClick_url(tb.getClickURL());
		item.setPic_url(tb.getPic_url());
		item.setTitle(tb.getTitle());
		if(Double.parseDouble(tb.getPrice_high())<=Double.parseDouble(tb.getPrice_low())){
			item.setDiscount("");
		}else{
			item.setDiscount(df.format(Double.parseDouble(tb.getPrice_low())/Double.parseDouble(tb.getPrice_high())*10));
		}
		item.setCommission_rate(tb.getRate()+"");
		return item;
	}
	
	public String getFromWeb(String uid,String dat){
		
		dat=getDat(dat, uid);
		String cc=NetManager.getInstance().send(add, "itemid="+dat);
		return cc;
	}
	
	public static String getDat(String dat,String uid){
		long t1=System.currentTimeMillis();
		String datt="";
		for(int i=0;i<dat.split(",").length;i++){
			String itemid=dat.split(",")[i];
			ItemMiddle im = new ItemMiddle();
			im=geiItem(im, itemid, uid);
			datt=datt+itemid+":"+(im.toXML4GuessYouLike())+",";
		}
		if(datt.length()>0){
			datt=datt.substring(0,datt.length()-1);
		}
		System.out.println("timeeeeeeee::::"+(System.currentTimeMillis()-t1));
		return datt;
	}
	
	public static ItemMiddle geiItem(ItemMiddle im,String itemId,String uid){
		im.setItemId(itemId);
//		im.setIsLikeItem(LikeItemManager.getInstance().isLikeItem(uid, itemId) ? 1 : 0);//用户是否喜欢该商品
		double rate = 0;
		if(TaobaoManager.isSupportFanli(itemId)){
			rate = JfbRateUtil.getRate(itemId);
			im.setJfbRate(rate);
		}
		return im;
	}
	
	public int getDay(){
		SimpleDateFormat sdf=new SimpleDateFormat("dd");
		String day=sdf.format(new Date());
		return Integer.parseInt(day);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}