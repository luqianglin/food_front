package cn.et.model.order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import cn.et.model.DbUntils;
/**
 * ����
 * @author Administrator
 *
 */
public class MyOrder {
	/**
	 * ��ȡ����id
	 * @return
	 * @throws Exception 
	 */
	public Integer getOrderId() throws Exception{
		String sql="select nvl(max(orderid),0)+1 as myId from FOODORDER";
		List<Map> result=DbUntils.query(sql);
		return Integer.parseInt((result.get(0).get("MYID").toString()));
	}
	/**
	 * ��Ӷ���
	 * @param deskId
	 * @param state
	 * @return
	 * @throws Exception
	 */
	public Integer saveOrder(String deskId,int state) throws Exception{
		Integer orderId=getOrderId();
		String sql="insert into FOODORDER values('"+orderId+"','"+deskId+"',sysdate,'"+state+"')";
		DbUntils.execute(sql);
		return orderId;
	}
	/**
	 * �����ϸ����
	 * @param orderId
	 * @param detail
	 * @throws Exception
	 */
	public void saveOrderDetail(Integer orderId,Integer foodId,Integer count) throws Exception{
		
			String sql="insert into FOODORDERDETAIL values((select nvl(max(detailid),0)+1 as myId from FOODORDERDETAIL),"
					+"'"+orderId+"','"+foodId+"','"+count+"')";
			DbUntils.execute(sql);		
		
	}
	
}
