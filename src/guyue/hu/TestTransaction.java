package guyue.hu;

import java.sql.*;
/**
 * 需要记住！！！！！！！！！！！！！！！！
 * 典型的事务处理过程
 * @author hgb22613
 *
 */
public class TestTransaction {

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
			
			conn.setAutoCommit(false);
			String sql = "insert into dept2 values (?, ?, ?)";
			stm = conn.prepareStatement(sql);
			stm.setInt(1, 1);
			stm.setString(2, "guyue");
			stm.setString(3, "guyue");
			stm.addBatch();
			
			stm.setInt(1, 2);
			stm.setString(2, "guyue");
			stm.setString(3, "guyue");
			stm.addBatch();
			
			stm.executeBatch();
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
				if(stm != null) stm.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
