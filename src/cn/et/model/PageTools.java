package cn.et.model;

import java.util.List;

/**
 * 鍒嗛〉鍙傛暟
 * @author Administrator
 *
 */
public class PageTools {
	/**
	 * 鏋勯�犲弬鏁�
	 * @param curPage 椤甸潰浼犲叆鐨勫綋鍓嶉〉
	 * @param totalCount 鏁版嵁搴撴煡璇㈢殑鎬昏褰曟暟
	 * @param pageCount 姣忛〉鏄剧ず鐨勬潯鏁�
	 */
	public PageTools(Integer curPage,Integer totalCount,Integer pageCount) {
		this.curPage=curPage;
		this.totalCount=totalCount;
		this.pageCount=pageCount==null?this.pageCount:pageCount;
		this.prePage=(curPage==1?1:curPage-1);
		this.totalPage=totalCount%this.pageCount==0?totalCount/this.pageCount:totalCount/this.pageCount+1;
		this.nextPage=(curPage==totalPage)?totalPage:(curPage+1);
		this.startIndex=(curPage-1)*this.pageCount+1;
		this.endIndex=curPage*this.pageCount;
		
	}
	
	/**
	 * 褰撳墠椤�(鍔ㄦ�� 鏈夐〉闈紶閫掔殑)
	 */
	private Integer curPage;
	/**
	 * 姣忛〉鏄剧ず鏉℃暟
	 */
	private Integer pageCount=10;
	/**
	 * 涓婁竴椤�
	 * prePage=(curPage==1?1:curPage-1);
	 * 涓句緥
	 * 		2 --- 1
	 * 		3 --- 2
	 * 		4 --- 3
	 * 		1 --- 1
	 * 
	 */
	private Integer prePage;
	/**
	 * 涓嬩竴椤�
	 * 	涓句緥
	 * 	nextPage=(curPage==totalPage)?totalPage:(curPage+1)
	 * 		curPage totalPage nextPage
	 * 			1		3		2	
	 * 			2		3		3
	 * 			3		3		3
	 */
	private Integer nextPage;
	/**
	 * 鎬婚〉鏁�
	 * pageCount(姣忛〉鏄剧ず鐨勬潯鏁�)  total(鎬昏褰曟暟) totalPage
	 * 	10						20				2
	 * 	10						21				3	
	 * 	totalPage=total%pageCount==0?total/pageCount:total/pageCount+1	
	 */
	private Integer totalPage;
	/**
	 * 鎬昏褰曟暟(浠庢暟鎹簱鏌ヨ)
	 */
	private Integer totalCount;
	/**
	 * 寮�濮嬬储寮�
	 * curPage pageCount start-end
	 * 1			10		1-10
	 * 2			10		11-20
	 * 						(curPage-1)*pageCount+1 curPage*pageCount
	 */
	private Integer startIndex;
	/**
	 * 缁撴潫绱㈠紩
	 */
	private Integer endIndex;
	
	/**
	 * 瀛樺偍鏈�缁堟煡璇㈢殑鏁版嵁
	 */
	private List data;
	
	
	public Integer getCurPage() {
		return curPage;
	}
	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getPrePage() {
		return prePage;
	}
	public void setPrePage(Integer prePage) {
		this.prePage = prePage;
	}
	public Integer getNextPage() {
		return nextPage;
	}
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}
	
	
	public static void main(String[] args) {
		int curPage=3;
		int totalCount=26;
		int pageCount=5;
		PageTools pt = new PageTools(curPage, totalCount, pageCount);
		
		System.err.println(pt.getNextPage());
		System.out.println(pt.getPrePage());
		System.out.println(pt.getTotalPage());
		System.out.println(pt.getStartIndex());
		System.out.println(pt.getEndIndex());
	}
}
