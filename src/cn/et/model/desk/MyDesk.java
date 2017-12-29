package cn.et.model.desk;

import java.util.List;
import java.util.Map;

import cn.et.model.DbUntils;
/**
 * 餐桌类
 * @author Administrator
 *
 */
public class MyDesk {
	/**
	 * 查找餐桌所有数据
	 * @return
	 * @throws Exception
	 */
	public List<Map> getTableListAll() throws Exception{
		String sql ="select * from desk";
		return DbUntils.query(sql);
	}
}
