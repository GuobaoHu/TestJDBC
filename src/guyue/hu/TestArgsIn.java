package guyue.hu;

import java.sql.*;

public class TestArgsIn {

	public static void main(String[] args) {
		if(args.length != 3) {
			System.out.println("please input 3 parameters!");
			System.exit(-1);
		}
		int deptno = 0;
		try {
			deptno = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			System.out.println("the format of deptno error!");
			System.exit(-1);
		}
		String dname = args[1];
		String loc = args[2];
		
		String className = "oracle.jdbc.driver.OracleDriver";
		String connUrl = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "guyue";
		String ps = "guyue";
		
		String sql = "insert into dept2 values (" + deptno + ", '" + dname + "', '" + loc + "')";
		System.out.println(sql);
		
		Connection conn = null;
		Statement stm = null;
		try {
			//1.load driver
			Class.forName(className);
			//2.connect database
			conn = DriverManager.getConnection(connUrl, user, ps);
			//3.insert data
			stm = conn.createStatement();
			stm.executeUpdate(sql);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
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
