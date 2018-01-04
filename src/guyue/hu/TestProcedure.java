package guyue.hu;

import java.sql.*;


public class TestProcedure {

	public static void main(String[] args) {
		String className = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "guyue";
		String psw = "guyue";
		Connection conn = null;
		CallableStatement stm = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(url, user, psw);
			String sql = "{call p_2(?, ?, ?, ?)}";
			stm = conn.prepareCall(sql);
			stm.registerOutParameter(3, Types.INTEGER);
			stm.registerOutParameter(4, Types.INTEGER);
			stm.setInt(1, 15);
			stm.setInt(2, 20);
			stm.setInt(4, 0);
			stm.execute();
			System.out.println(stm.getInt(3) + " " + stm.getInt(4));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
