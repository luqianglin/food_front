package cn.et.model.food_type;

import java.util.List;
import java.util.Map;

import cn.et.model.DbUntils;
/**
 * ��ϵ��
 * @author Administrator
 *
 */
public class MyFoodType {
	/**
	 * ���Ҳ�ϵ��������
	 * @return
	 * @throws Exception
	 */
	public List<Map> getAllFoodType() throws Exception{
		String sql="select * from FOODTYPE";
		return DbUntils.query(sql);
	}
	
	    
}
