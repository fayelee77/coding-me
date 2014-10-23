package cn.youhui.bean;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import cn.youhui.cacher.dao.Switch4JfbCacher;
import cn.youhui.common.Config;
import cn.youhui.utils.Util;

import com.google.gson.Gson;

public class TeJiaGoodsBean {
	/**
	 * @return the clickURL
	 */
	public String getClickURL() {
		return clickURL;
	}
	/**
	 * @param clickURL the clickURL to set
	 */
	public void setClickURL(String clickURL) {
		this.clickURL = clickURL;
	}
	/**
	 * @return the sellerNick
	 */
	public String getSellerNick() {
		return sellerNick;
	}
	/**
	 * @param sellerNick the sellerNick to set
	 */
	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}
	/**
	 * @return the discountType
	 */
	public String getDiscountType() {
		return discountType;
	}
	/**
	 * @param discountType the discountType to set
	 */
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	long interviewTime = 0;
	public long getInterviewTime() {
		return interviewTime;
	}
	public void setInterviewTime(long interviewTime) {
		this.interviewTime = interviewTime;
	}
//	String id = "";
	String item_id = "";
	String title = "";
	int category_id = 0;
	String keyword = "";
	private int show_index;
	String price_high = "";
	String price_low ="";
	String discount = "";
	String discountType = "" ;
	String pic_url = "";
	int status = 0 ;
	String recent_sell = "";
	String taobao_time_limit = "";
	long start_time = 0;
	long end_time = 0; 
	String sellerNick = "" ;
	String clickURL = "" ;
	long update_time = 0;
	
	private double rate = 0;
	
	private String catID;
	
	private String recoReason;
	private String picCutUrl; 
	
	
	private int width_b;
	private int height_b;
	private int width_m;
	private int height_m;
	private int width_310;
	private int height_310;
	private int width_170;
	private int height_170;
	
	private int baoyou = 0;
	private String tagId = "";
	
	
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	public int getBaoyou() {
		return baoyou;
	}
	public void setBaoyou(int baoyou) {
		this.baoyou = baoyou;
	}
	public String getPicCutUrl() {
		return picCutUrl;
	}
	public void setPicCutUrl(String picCutUrl) {
		this.picCutUrl = picCutUrl;
	}
	public String getRecoReason() {
		return recoReason;
	}
	public void setRecoReason(String recoReason) {
		this.recoReason = recoReason;
	}
	public String getCatID() {
		return catID;
	}
	public void setCatID(String catID) {
		this.catID = catID;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public int getWidth_b() {
		return width_b;
	}
	public void setWidth_b(int width_b) {
		this.width_b = width_b;
	}
	public int getHeight_b() {
		return height_b;
	}
	public void setHeight_b(int heigh_b) {
		this.height_b = heigh_b;
	}
	public int getWidth_m() {
		return width_m;
	}
	public void setWidth_m(int width_m) {
		this.width_m = width_m;
	}
	public int getHeight_m() {
		return height_m;
	}
	public void setHeight_m(int heigh_m) {
		this.height_m = heigh_m;
	}
	public int getWidth_310() {
		return width_310;
	}
	public void setWidth_310(int width_310) {
		this.width_310 = width_310;
	}
	public int getHeight_310() {
		return height_310;
	}
	public void setHeight_310(int heigh_310) {
		this.height_310 = heigh_310;
	}
	String commission = "";
	String commission_rate = "";
	
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getPrice_high() {
		return price_high;
	}
	public void setPrice_high(String price_high) {
		this.price_high = price_high;
	}
	public String getPrice_low() {
		return price_low;
	}
	public void setPrice_low(String price_low) {
		this.price_low = price_low;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getPic_url() {
		return pic_url;
	}
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRecent_sell() {
		return recent_sell;
	}
	public void setRecent_sell(String recent_sell) {
		this.recent_sell = recent_sell;
	}
	public String getTaobao_time_limit() {
		return taobao_time_limit;
	}
	public void setTaobao_time_limit(String taobao_time_limit) {
		this.taobao_time_limit = taobao_time_limit;
	}
	public long getStart_time() {
		return start_time;
	}
	public void setStart_time(long start_time) {
		this.start_time = start_time;
	}
	public long getEnd_time() {
		return end_time;
	}
	public void setEnd_time(long end_time) {
		this.end_time = end_time;
	}
	public long getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(long update_time) {
		this.update_time = update_time;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getKeyword() {
		return keyword;
	}
	
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	
	public String getCommission_rate() {
		return commission_rate;
	}
	public void setCommission_rate(String commission_rate) {
		this.commission_rate = commission_rate;
	}
	public String toJson(){
		Gson g = new Gson();
		return g.toJson(this);
	}
	public void setShow_index(int show_index) {
		this.show_index = show_index;
	}
	public int getShow_index() {
		return show_index;
	}
	public int getWidth_170() {
		return width_170;
	}
	public void setWidth_170(int width_170) {
		this.width_170 = width_170;
	}
	public int getHeight_170() {
		return height_170;
	}
	public void setHeight_170(int height_170) {
		this.height_170 = height_170;
	}
	
    public String toXML(){
		
//    	if("0".equals(width_170) && item_id.indexOf("3") == 2){
//    		width_170 = 100;
//    		height_170 = 100;
//    		width_m = 100;
//    		height_m = 100;
//    	}
    	
    	
    	
    	double discountD = 1.00;
		DecimalFormat df = new DecimalFormat("#0.00");
		try {
			double hPrice = Double.parseDouble(price_high);
			double lPrice = Double.parseDouble(price_low);
			if(hPrice != 0 && hPrice >= lPrice){
				discountD = lPrice / hPrice;
			}else if(lPrice != 0){
				String tpP = price_high;
				price_high = price_low;
				price_low = tpP;
				
				discountD = hPrice / lPrice;
			}
		} catch (Exception e) {
			discountD = 1.00;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<item>");
		sb.append("<item_id>" + item_id
				+ "</item_id>");
		sb.append("<jfb_rate>" + rate +"</jfb_rate>");
		sb.append("<title><![CDATA["
				+ title.replaceAll("<[^>]+>", "")
				+ "]]></title>");
		sb.append("<img_big width=\""
				+ width_170
				+ "\" heigh=\""
				+ height_170
				+ "\"><![CDATA["
				+ Util.getCustomImg(pic_url, "170x170") + "]]></img_big>");
		sb.append("<img_small width=\"" + width_m
				+ "\" heigh=\"" + height_m
				+ "\"><![CDATA["
				+ Util.getCustomImg(pic_url, "170x170") + "]]></img_small>");
		sb.append("<price phi=\""
				+ computer(price_high)
				+ "\" plow=\""
				+ computer(price_low)
				+ "\" off=\""
				+ (int)(discountD*10000) + "\"></price>");
//		if (commission != null && !commission.equals("")){
//			try {
//				sb.append("<commission value=\""
//						+ new DecimalFormat("0.00").format(Double.parseDouble(commission) * 0.6)
//						+ "\" rate=\""
//						+ new DecimalFormat("0.00").format(Double.parseDouble(commission_rate) * 0.6)
//						+ "\"></commission>");
//			}catch (Exception e){
//			}
//		} else {
//			double discountD = 10.00;
//			DecimalFormat df = new DecimalFormat("#0.00");
//			try {
//				discountD = Double.parseDouble(price_low)*10/ Double.parseDouble(price_high);
//			} catch (Exception e) {
//				discountD = 10.00;
//			}
		
			sb.append("<discount>").append(df.format(discountD*10)).append("</discount>");
			sb.append("<commission value=\"0\" rate=\""+ commission_rate + "\" />");
//		}
			
//		if (clickURL.equals("")) {
//			sb.append("<click><![CDATA[http://a.m.taobao.com/i"+ item_id + ".htm]]></click>");
//		} else {
//			sb.append("<click><![CDATA[" + clickURL + "]]></click>");
//			
//		}
			
	//	sb.append("<click><![CDATA["+Config.SKIP_URL+"itemid="+item_id+"&uid="+uid+"&tag_id="+tagId+"&jfb_rate="+rate+"&from_channel=7&from_value="+tagId+"]]></click>");
		sb.append("<click><![CDATA[" + clickURL + "]]></click>");
		sb.append("<is_baoyou>").append(baoyou).append("</is_baoyou>");
	    sb.append("</item>");
		return sb.toString();
	}

    
    public String toXMLBaoyouRelated(boolean showBaoyou){  
    	
    	String jfbSwitch=Switch4JfbCacher.getInstance().getSwitch();
    	if(jfbSwitch.equals("0")){
    		rate=0;
    	}
    	
    	double discountD = 1.00;
		DecimalFormat df = new DecimalFormat("#0.00");
		try {
			double hPrice = Double.parseDouble(price_high);
			double lPrice = Double.parseDouble(price_low);
			if(hPrice != 0 && hPrice >= lPrice){
				discountD = lPrice / hPrice;
			}else if(lPrice != 0){
				String tpP = price_high;
				price_high = price_low;
				price_low = tpP;
				
				discountD = hPrice / lPrice;
			}
		} catch (Exception e) {
			discountD = 1.00;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<item>");
		sb.append("<item_id>" + item_id
				+ "</item_id>");
		sb.append("<jfb_rate>" + rate +"</jfb_rate>");
		sb.append("<title><![CDATA["
				+ title.replaceAll("<[^>]+>", "")
				+ "]]></title>");
		sb.append("<img_big width=\""
				+ width_170
				+ "\" heigh=\""
				+ height_170
				+ "\"><![CDATA["
				+ Util.getSimpleImg(pic_url, "170x170") + "]]></img_big>");
		sb.append("<img_small width=\"" + width_m
				+ "\" heigh=\"" + height_m
				+ "\"><![CDATA["
				+ Util.getSimpleImg(pic_url, "170x170") + "]]></img_small>");
		sb.append("<price phi=\""
				+ computer(price_high)
				+ "\" plow=\""
				+ computer(price_low)
				+ "\" off=\""
				+ (int)(discountD*10000) + "\"></price>");

		
		sb.append("<discount>").append(df.format(discountD*10)).append("</discount>");
		sb.append("<commission value=\"0\" rate=\""+ commission_rate + "\" />");
		sb.append("<click><![CDATA[" + clickURL + "]]></click>");
		
		if(showBaoyou){ 
			sb.append("<is_baoyou>").append(baoyou).append("</is_baoyou>");
		}else{	//超级惠不显示包邮
			sb.append("<is_baoyou>0</is_baoyou>");
		}
		
	    sb.append("</item>");
		return sb.toString();
	}
    
	public String toXMLold(){
		double priceHi = computer(price_high);
		double priceLow = computer(price_low);
		double disc = 0;
		if(priceHi >= priceLow){
			disc = priceLow/priceHi;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<item>");
		sb.append("<item_id>" + item_id
				+ "</item_id>");
		sb.append("<title><![CDATA["
				+ title.replaceAll("<[^>]+>", "")
				+ "]]></title>");
		sb.append("<img_big width=\""
				+ width_170
				+ "\" heigh=\""
				+ height_170
				+ "\"><![CDATA["
				+ Util.getSimpleImg(pic_url, "170x170") + "]]></img_big>");
		sb.append("<img_small width=\"" + width_m
				+ "\" heigh=\"" + height_m
				+ "\"><![CDATA["
				+ Util.getSimpleImg(pic_url, "170x170") + "]]></img_small>");
		sb.append("<price phi=\""
				+ priceHi
				+ "\" plow=\""
				+ priceLow
				+ "\" off=\""
				+ disc + "\"></price>");
		sb.append("<is_baoyou>").append(baoyou).append("</is_baoyou>");
		double discountD = 10.00;
		DecimalFormat df = new DecimalFormat("#0.00");
		try {
			discountD = Double.parseDouble(price_low)*10/ Double.parseDouble(price_high);
		} catch (Exception e) {
			discountD = 10.00;
		}
		sb.append("<discount>").append(df.format(discountD)).append("</discount>");
		if (commission != null && !commission.equals("")){
			try {
				sb.append("<commission value=\""
						+ new DecimalFormat("0.00").format(Double.parseDouble(commission) * 0.6)
						+ "\" rate=\""
						+ new DecimalFormat("0.00").format(Double.parseDouble(commission_rate) * 0.6)
						+ "\"></commission>");
			}catch (Exception e){
			}
		} else {
			sb.append("<commission value=\"0\" rate=\""+ commission_rate + "\" />");
		}
		if (clickURL.equals("")) {
			sb.append("<click><![CDATA[http://a.m.taobao.com/i"+ item_id + ".htm]]></click>");
		} else {
			sb.append("<click><![CDATA[" + clickURL + "]]></click>");
			
		}
		sb.append("</item>");
		return sb.toString();
	}
	
	private double computer(String priceStr) {
		double price = 0;
		try {
			if (priceStr != null && priceStr.length() != 0)
				price = Double.parseDouble(priceStr);
		} catch (Exception e) {
		}
		BigDecimal b1 = new BigDecimal(Double.toString(price));
		BigDecimal b2 = new BigDecimal(Double.toString(100));
		return b1.multiply(b2).doubleValue();
	}
	
	private String getSmallImg(String pic_url) {
		pic_url = pic_url == null ? "" : pic_url;
		if (!pic_url.equals("")) {
			if(pic_url.contains("bcs.duapp.com")){
				String form = pic_url.substring(pic_url.lastIndexOf("."));
				return pic_url + "_m" + form;
			}
			if (pic_url.indexOf("_b.jpg") != -1) {
				pic_url = pic_url.replace("_b.jpg", "") + "_m.jpg";
			} else if (pic_url.indexOf("_m.jpg") == -1) {
				pic_url = pic_url + "_m.jpg";
			}
		}
		return pic_url;
	}
}