package guyue.hu;

import java.sql.*;

public class TestScroll {

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
			stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stm.executeQuery("select * from emp order by empno desc");
			rs.next();
			System.out.println(rs.getInt(1) + rs.getString(2));
			System.out.println(rs.isFirst());
			
			rs.last();
			System.out.println(rs.getInt(1) + rs.getString(2));
			System.out.println(rs.isLast());
			System.out.println(rs.isAfterLast());
			
			rs.previous();
			System.out.println(rs.getInt(1) + rs.getString(2));
			System.out.println(rs.isLast());
			System.out.println(rs.isAfterLast());
			
			rs.absolute(14);
			System.out.println(rs.getInt(1) + rs.getString(2));
			System.out.println(rs.isLast());
			System.out.println(rs.isAfterLast());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(stm != null) stm.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
