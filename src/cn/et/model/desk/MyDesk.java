package cn.et.model.desk;

import java.util.List;
import java.util.Map;

import cn.et.model.DbUntils;
/**
 * ������
 * @author Administrator
 *
 */
public class MyDesk {
	/**
	 * ���Ҳ�����������
	 * @return
	 * @throws Exception
	 */
	public List<Map> getTableListAll() throws Exception{
		String sql ="select * from desk";
		return DbUntils.query(sql);
	}
}
