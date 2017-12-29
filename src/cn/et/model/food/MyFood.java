package cn.et.model.food;

import java.util.List;
import java.util.Map;

import cn.et.model.DbUntils;
import cn.et.model.PageTools;
/**
 * 菜品类
 * @author Administrator
 *
 */
public class MyFood {
	/**
	 * 获取总条数
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Integer getTableListCount(String typeId) throws Exception{
		
		String sql="select count(rowid) as cr from food t where t.typeid like '%"+typeId+"%'";

		List<Map> result=DbUntils.query(sql);
		return Integer.parseInt(result.get(0).get("CR").toString());
	}
	/**
	 * 封装了get方法
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public PageTools getTableListPager(Integer curPage,String typeId) throws Exception{
		
		Integer totalCount=getTableListCount(typeId);
		PageTools pt=new PageTools(curPage, totalCount, 6);
		StringBuffer sb = new StringBuffer();
		String sql="select * from (select t.*,ft.typename,rownum rn from food t inner join foodtype ft on t.typeid=ft.typeid where t.typeid like '%"+typeId+"%')"
				+ " where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex();
		
		List<Map> result=DbUntils.query(sql);
		pt.setData(result);
		return pt;
	}
	/**
	 * 菜品查询
	 * @param deskId
	 * @throws Exception
	 */
	public void selectDesk(String foodName) throws Exception{
		String sql="select * from FOOD where foodname like '%"+foodName;
		DbUntils.execute(sql);
		
	}
	/**
	 * 查询详细菜
	 * @param foodid
	 * @return
	 * @throws Exception 
	 */
	public List<Map> getCaiXiangxi(String foodid) throws Exception{
		String sql="select * from food where foodid="+foodid;
		List<Map> list=DbUntils.query(sql);
		return list;
	}
	/**
	 * 查看菜品
	 * @return
	 * @throws Exception
	 */
	public List<Map> getAllFood() throws Exception{
		String sql="select * from FOOD";
		return DbUntils.query(sql);
	}
}
