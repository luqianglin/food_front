package cn.et.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


/**
 * jdbc閿熸枻鎷疯閿熸枻鎷�
 * @author Administrator
 *
 */
public class DbUntils {
	//閿熸枻鎷烽敓鑺傝鎷峰彇java閿熸枻鎷烽敓鏂ゆ嫹閿熶茎纭锋嫹
	static Properties p = new Properties();
	static{
		InputStream is = DbUntils.class.getResourceAsStream("jdbc.properties");
		try {
			p.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 获取连接
	 *
	 */
	public static Connection getConnection() throws Exception{
		String url = p.getProperty("url");
		String driverClass = p.getProperty("driverClass");
		String uname = p.getProperty("username");
		String password = p.getProperty("password");
		Class.forName(driverClass);
		//閿熸枻鎷峰綍閿熺即鐧告嫹
		Connection conn = DriverManager.getConnection(url,uname,password);
		return conn;
	}
	/**
	 * java缂佹挻鐎�
	 * 婢舵俺顢慙ist list=new ArrayList 婢舵矮閲滅悰灞藉讲娴犮儳绮嶉幋鎰娑擃亪娉﹂崥鍧檈ngth=2
	 * 1鐞涳拷
	 * 	Map map =new HashMap()
	 * 	map.put("id",1)
	 * 	map.put("id",1)
	 * Map map1 =new HashMap()
	 * 	map1.put("id",2)
	 * 	map1.put("name",li)
	 * List list =new ArrayList
	 * 	list.add(map)
	 * 	list.add(map1)
	 * @throws Exception 
	 * 
	 */
	public static  List<Map> query(String sql) throws Exception{
		Connection conn = getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		List list = new ArrayList<>();
		//閼惧嘲褰囬崚妤冩畱娑擃亝鏆�
		int columnCount = rsmd.getColumnCount();
		while(rs.next()){
			Map map = new HashMap<>();
			for(int i=1; i<=columnCount; i++){
				String colName = rsmd.getColumnName(i);
				String colValue = rs.getString(i);
				map.put(colName, colValue);
					
			}
			list.add(map);
		}
		rs.close();
		pst.close();
		conn.close();
		return list;
		
	}
	public static int execute(String sql) throws Exception{
		Connection conn = getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		int i=pst.executeUpdate();
		pst.close();
		conn.close();
		return i;
	}
	
	public static void main(String[] args) throws Exception {
		List<Map> result = query("select * from users");
		System.out.println(result);
	}
}
