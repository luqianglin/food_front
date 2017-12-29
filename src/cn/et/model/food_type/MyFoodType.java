package cn.et.model.food_type;

import java.util.List;
import java.util.Map;

import cn.et.model.DbUntils;
/**
 * 菜系类
 * @author Administrator
 *
 */
public class MyFoodType {
	/**
	 * 查找菜系所有数据
	 * @return
	 * @throws Exception
	 */
	public List<Map> getAllFoodType() throws Exception{
		String sql="select * from FOODTYPE";
		return DbUntils.query(sql);
	}
	
	    
}
