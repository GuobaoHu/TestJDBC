package guyue.hu;

import java.sql.*;

public class TestTrans {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "guyue";
		String psw = "guyue";
		Connection conn = null;
		PreparedStatement preStm = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, psw);
			
			conn.setAutoCommit(false);
			String sql = "update emp2 set sal = sal + ? where deptno = ?";
			preStm = conn.prepareStatement(sql);
			
			preStm.setDouble(1, 8.88);
			preStm.setInt(2, 20);
			preStm.addBatch();
			
			preStm.setDouble(1, 9.99);
			preStm.setInt(2, 30);
			preStm.addBatch();
			
			preStm.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				if(conn != null) {
					conn.rollback();
					conn.setAutoCommit(true);
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		} finally {
			try {
				if(preStm != null) preStm.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
