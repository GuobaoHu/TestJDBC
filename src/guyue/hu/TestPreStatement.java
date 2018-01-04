package guyue.hu;

import java.sql.*;

public class TestPreStatement {

	public static void main(String[] args) {
		//ͨ��args��ȡsql�������������
		int deptno = 0;
		String dname = null;
		String loc = null;
		//�������ݿ���ص�����
		String className = "oracle.jdbc.driver.OracleDriver";
		String connUrl = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "guyue";
		String ps = "guyue";
		Connection conn = null;
		PreparedStatement preStm = null;
		//1.��ȡsql���Ĳ���
		if(args.length != 3) {
			System.out.println("Need 3 Parameters!");
			System.exit(-1);
		} else {
			try {
				deptno = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				System.out.println("deptno format error!");
				System.exit(-1);
			}
			dname = args[1];
			loc = args[2];
		}
		//2.�������ݿⲢ����ֵ
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(connUrl, user, ps);
			preStm = conn.prepareStatement("insert into dept2 values(?,?,?)");
			preStm.setInt(1, deptno);
			preStm.setString(2, dname);
			preStm.setString(3, loc);
			preStm.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
