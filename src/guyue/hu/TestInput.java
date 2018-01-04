package guyue.hu;

import java.io.*;
import java.sql.*;

public class TestInput {

	public static void main(String[] args) {
		new TestInput().launch();
	}
	
	public void launch() {
		String className = "oracle.jdbc.driver.OracleDriver";
		String connStr = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "guyue";
		String ps = "guyue";
		System.out.println("请输入deptno值:");
		int deptno = Integer.parseInt(getInput());
		System.out.println("请输入dname值:");
		String dname = getInput();
		System.out.println("请输入Loc值：");
		String loc = getInput();
		
		Connection conn = null;
		Statement stm = null;
		try {
			String InsertSql = "insert into dept2 values(" + deptno + "," + "'" + dname + "'," + "'" + loc + "'" + ")";
			//1.载入驱动,自动注册DriverManager
			Class.forName(className);
			//2.连接数据库
			conn = DriverManager.getConnection(connStr, user, ps);
			//3.插入数据库
			stm = conn.createStatement();
			stm.executeUpdate(InsertSql);
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
	
	/**
	 * 获取从面板的输入
	 * @return 以字符串格式返回输入的数据
	 */
	public String getInput() {
		String str = null;
		try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

}
