package estore.model;

import java.util.Enumeration;

import org.apache.struts2.ServletActionContext;

import estore.util.XWeb;

public class PagerModel {
	public int pageNo, pageSize = 10, rowCount;
	
	public static PagerModel getPager(String id, int pageSize) {
		PagerModel pager = getPager(id);
		pager.setPageSize(pageSize);
		return pager;
	}
	
	public static PagerModel getPager(String id) {
		PagerModel pager = (PagerModel) XWeb.getSession(id);
		if(pager == null){
			XWeb.setSession(id, pager = new PagerModel());
		}
		return pager;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getStartRow() {
		return pageNo*pageSize;
	}
	
	public int getPageCount() {
		return (int) Math.ceil(1.0*rowCount/pageSize);
	}
	
	public int getLastPageNo() {
		return getPageCount() - 1;
	}
	
	public void navigate(int pageNo) {
		if(pageNo < 0){
			this.pageNo = getLastPageNo();
		}
		else if(pageNo > getLastPageNo()){
			this.pageNo = 0;
		}
		else{
			this.pageNo = pageNo;
		}
	}

	public void navigate() {
		Enumeration<String> names = ServletActionContext.getRequest().getParameterNames();
		while(names.hasMoreElements()){
			String name = names.nextElement().toLowerCase();
			int i = name.lastIndexOf("pageno:");
			if(i >= 0){
				this.pageNo = Integer.parseInt(name.substring(i + 7));
				break;
			}
		}
		this.navigate(this.pageNo);
	}
	
	/*
	 * Paging by group
	 */
	public int groupSize = 5;
	
	public static PagerModel getPager(String id, int pageSize, int groupSize) {
		PagerModel pager = getPager(id, pageSize);
		pager.setGroupSize(groupSize);
		return pager;
	}

	public int getGroupSize() {
		return groupSize;
	}

	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}

	public int getGroupNo() {
		return pageNo/groupSize;
	}

	public int getGroupCount() {
		return (int) Math.ceil(1.0*getPageCount()/groupSize);
	}
	
	public int getLastGroupNo() {
		return getGroupCount() - 1;
	}
	
	public int getStartPage() {
		return getGroupNo()*groupSize;
	}
	
	public int getEndPage() {
		if(getGroupNo() == getLastGroupNo()){
			return getLastPageNo();
		}
		return getStartPage() + groupSize - 1;
	}
	
	public String getGroupNavigationBar() {
		StringBuffer links = new StringBuffer();
		for(int i=getStartPage();i<=getEndPage();i++){
			links.append("<a href='?pageno="+i+"#tabList'>"+(1+i)+"</a>");
		}
		return links.toString();
	}
}
