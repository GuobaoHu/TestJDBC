package guyue.hu;

import java.sql.*;
/**
 * oracle暂时不支持更新结果集，其他数据库可能支持，以下代码作为参考
 * @author hgb22613
 *
 */

public class TestUpdateRs {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "guyue";
		String psw = "guyue";
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, psw);
			stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stm.executeQuery("select * from emp2");
			
			rs.absolute(5);
			System.out.println(rs.getInt(1) + " " + rs.getString(2));
			//更新某行	
			rs.absolute(10);
			rs.updateString(2, "ABCDEFG");
			//插入某行
			rs.moveToInsertRow();
			rs.updateInt(1, 9999);
			rs.updateString(2, "AAAAAA");
			rs.updateString(3, "ADM");
			rs.insertRow();
			
			//将光标移动到新建的行
			rs.moveToCurrentRow();
			
			//删除某行
			rs.absolute(14);
			rs.deleteRow();
			
			//取消更新
			rs.cancelRowUpdates();
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
