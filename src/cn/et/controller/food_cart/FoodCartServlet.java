package cn.et.controller.food_cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.et.model.order.MyOrder;



/**
 * Servlet implementation class FoodCartServlet
 */
public class FoodCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    MyOrder order=new MyOrder();
	/**
	 * session
	 * 	key --- value
	 * 	fid		
	 * 	����		map{
	 * 				fid:1
	 * 				fname:���м�
	 * 				price:100
	 * 				count:2
	 * 			}
	 *
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String flag = request.getParameter("flag");
		//�����ַ���
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//respnose��Ӧҳ��
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession();
		
		//�ж��Ƿ�ɾ�� �� ����flag����ɾ��
		if("delete".equals(flag)){
			String fid = request.getParameter("fid");
			session.removeAttribute(fid);
			int sum=0;
			Enumeration keys=session.getAttributeNames();
			while(keys.hasMoreElements()){
				String key =(String) keys.nextElement();
				if(key.startsWith("cart_")){
					Map map =(Map) session.getAttribute(key);
					String count=  map.get("count").toString();
					String price= map.get("price").toString();
					int count1=Integer.parseInt(count);
					int price1=Integer.parseInt(price);
					sum=sum+count1*price1;
				}
			}
			session.setAttribute("sum",sum);
			request.getRequestDispatcher("/detail/clientCart.jsp").forward(request, response);
		}else if("order".equals(flag)){
			//��session���ȡ����id
			//System.out.println("1");
			String deskId = session.getAttribute("deskId").toString();
			Integer  sum=(Integer) session.getAttribute("sum");
			int state=0;
			try {
				Integer orderId = order.saveOrder(deskId,state);
				//ѭ��session�����еļ� cart_1
				Enumeration<String> keys = session.getAttributeNames();
				while(keys.hasMoreElements()){
					String key = keys.nextElement();
					
					//�ж��Ƿ��м����cart_ ����н���
					if(key.startsWith("cart_")){
						String fid = key.split("cart_")[1];
						Map map = (Map)session.getAttribute(key);
						Integer count = (Integer)map.get("count");
						 int i=Integer.parseInt(fid);
						 
						order.saveOrderDetail(orderId,i, count);
					}
				}
				//session.invalidate();
				pw.write("<script>alert('�µ��ɹ�')</script>");
				//1�����תҳ��
				response.setHeader("refresh", "1;url="+request.getContextPath()+"/ShowDesk");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			//��Ʒid
			String fid = request.getParameter("fid");
			String foodName = request.getParameter("foodName");
			String price = request.getParameter("price");
			
			//��ǰ��Ʒ��һ�α�����ͳ�	 seeeion������  ��Ӹò�Ʒ��session�� ����1
			
			Object food = session.getAttribute("cart_"+fid);
			//��һ�μ���
			if(food==null){
				Map map = new HashMap();
				map.put("foodName", foodName);
				map.put("price", price);
				map.put("count", 1);
				session.setAttribute("cart_"+fid, map);
			}else{
				Map map =(Map)food;
				Integer in= (Integer)map.get("count");
				map.put("count", in+1);
				//�ֲ�ʽ����
				session.setAttribute("cart_"+fid, map);
			}
			int sum=0;
			Enumeration keys=session.getAttributeNames();
			while(keys.hasMoreElements()){
				String key =(String) keys.nextElement();
				if(key.startsWith("cart_")){
					Map map =(Map) session.getAttribute(key);
					String countTmp=  map.get("count").toString();
					String priceTmp= map.get("price").toString();
					int count1=Integer.parseInt(countTmp);
					int price1=Integer.parseInt(priceTmp);
					sum=sum+count1*price1;
				}
			}
			session.setAttribute("sum",sum);
			request.getRequestDispatcher("/detail/clientCart.jsp").forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
