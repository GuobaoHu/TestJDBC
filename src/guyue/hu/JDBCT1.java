package guyue.hu;

import java.sql.*;

public class JDBCT1 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "guyue", "guyue");
			stm = conn.createStatement();
			rs = stm.executeQuery("select deptno from dept");
			while(rs.next()) {
				System.out.println(rs.getInt("deptno"));
			}
			stm.executeUpdate("delete from dept where deptno = 50");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
					rs = null;
				}
				if(stm != null) {
					stm.close();
					stm = null;
				}
				if(conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
