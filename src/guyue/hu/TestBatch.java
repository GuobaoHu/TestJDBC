package guyue.hu;

import java.sql.*;

public class TestBatch {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "guyue";
		String psw = "guyue";
		Connection conn = null;
		PreparedStatement stm = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, psw);
			String sql = "insert into dept2 values (?, ?, ?)";
			stm = conn.prepareStatement(sql);
			
			stm.setInt(1, 99);
			stm.setString(2, "hello");
			stm.setString(3, "mine");
			stm.addBatch();
			
			stm.setInt(1, 77);
			stm.setString(2, "hello");
			stm.setString(3, "mine");
			stm.addBatch();
			
			stm.setInt(1, 55);
			stm.setString(2, "hello");
			stm.setString(3, "mine");
			stm.addBatch();
			
			stm.executeBatch();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stm != null) stm.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
