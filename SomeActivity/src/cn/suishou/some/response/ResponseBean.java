package cn.suishou.some.response;

import java.util.List;

public class ResponseBean extends BaseBean{
	private HeaderBean head;
	private BaseBean data;
	private List<BaseBean> beanlist;
	private String listname;
	private int page = 0;
	private int pageTotal = 0;
	private String format = "json";

	
	public ResponseBean(){
	}
	
	public ResponseBean(HeaderBean head, String format){
		this.head = head;
		this.format = format;
	}
	
	public ResponseBean(HeaderBean head,BaseBean data, String format){
		this.head = head;
		this.data = data;
		this.format = format;
	}
	
	public ResponseBean(HeaderBean head,List list, String listname, String format){
		this.head = head;
		this.beanlist = list;
		this.listname = listname;
		this.format = format;
	}
	
	
	public ResponseBean(HeaderBean head,List list, String listname, int page, int pageTotal, String format){
		this.head = head;
		this.beanlist = list;
		this.listname = listname;
		this.page = page;
		this.pageTotal = pageTotal;
		this.format = format;
	}
	
	public ResponseBean(HeaderBean head,BaseBean data, List list, String listname, int page, int pageTotal, String format){
		this.head = head;
		this.data = data;
		this.beanlist = list;
		this.listname = listname;
		this.page = page;
		this.pageTotal = pageTotal;
		this.format = format;
	}
	
	public String toXML() {
		StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<resp>");
		sb.append(head.toXML());
		if(beanlist != null && beanlist.size() > 0){
			sb.append("<data>");
			if(data != null){
				sb.append(data.toXML());
			}
			sb.append("<page current=\"" + page + "\" total=\"" + pageTotal +"\"></page>");
			sb.append("<" + listname +">");
			for(BaseBean bean : beanlist){
				sb.append(bean.toXML());
			}
			sb.append("</" + listname + "></data>");
		}else if(data != null){
			sb.append("<data>");
			sb.append(data.toXML());
			sb.append("</data>");
		}
		sb.append("</resp>");
		return sb.toString();
	}
	
	public String toJson(){
		StringBuffer sb = new StringBuffer();
		sb.append("{\"resp\":{")
		.append(head.toJson());
		 if(beanlist != null && beanlist.size() > 0){
			sb.append(",\"data\":{");
			sb.append("\"page\":")
			.append("{\"current\":\"").append(page).append("\",")
			.append("\"total\":\"").append(pageTotal).append("\"")
			.append("},");
			sb.append("\""+listname+"\":[");
			for(BaseBean bean : beanlist){
				sb.append(bean.toJson()+",");
			}
		    return sb.substring(0, sb.length()-1) + "]}}}";
		}else if(data != null){
			sb.append(",\"data\":");
			sb.append( data.toJson());
		return sb.append("}}").toString();
		}else{
			return sb.append("}}").toString();
		}
	}
	
	public String toString(){
		if("json".equalsIgnoreCase(format))
			return toJson();
		else return toXML();
	}
}