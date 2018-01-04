package guyue.hu;

import java.sql.*;

public class TestDML {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stm = null;
		String conUrl = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "guyue";
		String pw = "guyue";
		String sqlInsert = "insert into dept2 values(70, 'DV', 'SH')";
		String sqlDelete = "delete from dept2 where deptno = 70";
		try {
			//1.load driver,���Զ�ע��DriverManager
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.�������ݿ�
			conn = DriverManager.getConnection(conUrl, user, pw);
			//3.����SQL���ִ�е�Statement����ִ����ɾ�Ĳ�
			stm = conn.createStatement();
			stm.executeUpdate(sqlInsert);
			//4.������
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//5.�ر�����
			try {
				if(stm != null) {
					stm.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
