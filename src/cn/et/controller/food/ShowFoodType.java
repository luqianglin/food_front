package cn.et.controller.food;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.et.model.PageTools;
import cn.et.model.food.MyFood;
import cn.et.model.food_type.MyFoodType;

/**
 * Servlet implementation class ShowFoodType
 */
public class ShowFoodType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowFoodType() {
        super();
        // TODO Auto-generated constructor stub
    }
    MyFoodType mft = new MyFoodType();
    MyFood mf = new MyFood();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��һ�ν���  û�в�ϵ ��ѯ����
		String typeId="";
		
		try {
			if(request.getParameter("typeId")!=null){
				typeId=request.getParameter("typeId");
			}
			//��ǰ����Ĳ�����Ŵ���session Ϊ����׼���µ�ʹ��
			HttpSession session=request.getSession();
			//���request��deskId ��������
			if(request.getParameter("deskId")!=null){
			//System.out.println("2"+ request.getParameter("deskId"));
				session.setAttribute("deskId", request.getParameter("deskId"));
				
			}
			// ��ϵ
			//request.setAttribute("tableList", mft.getAllFoodType());
			String foodName=request.getParameter("foodName");
			String curPage=request.getParameter("curPage");
			Integer curPageInt=1;
			if(curPage!=null){
				curPageInt=Integer.parseInt(curPage);
			}
			//���в�Ʒ
			PageTools tableListPager = mf.getTableListPager(curPageInt,typeId);
			request.setAttribute("foodList", tableListPager);
			request.getRequestDispatcher("/detail/caidan.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
