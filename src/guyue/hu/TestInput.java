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
		System.out.println("������deptnoֵ:");
		int deptno = Integer.parseInt(getInput());
		System.out.println("������dnameֵ:");
		String dname = getInput();
		System.out.println("������Locֵ��");
		String loc = getInput();
		
		Connection conn = null;
		Statement stm = null;
		try {
			String InsertSql = "insert into dept2 values(" + deptno + "," + "'" + dname + "'," + "'" + loc + "'" + ")";
			//1.��������,�Զ�ע��DriverManager
			Class.forName(className);
			//2.�������ݿ�
			conn = DriverManager.getConnection(connStr, user, ps);
			//3.�������ݿ�
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
	 * ��ȡ����������
	 * @return ���ַ�����ʽ�������������
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
