package guyue.hu;

import java.sql.*;
/**
 * oracle��ʱ��֧�ָ��½�������������ݿ����֧�֣����´�����Ϊ�ο�
 * @author hgb22613
 *
 */

public class TestUpdateRs {

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
			stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stm.executeQuery("select * from emp2");
			
			rs.absolute(5);
			System.out.println(rs.getInt(1) + " " + rs.getString(2));
			//����ĳ��	
			rs.absolute(10);
			rs.updateString(2, "ABCDEFG");
			//����ĳ��
			rs.moveToInsertRow();
			rs.updateInt(1, 9999);
			rs.updateString(2, "AAAAAA");
			rs.updateString(3, "ADM");
			rs.insertRow();
			
			//������ƶ����½�����
			rs.moveToCurrentRow();
			
			//ɾ��ĳ��
			rs.absolute(14);
			rs.deleteRow();
			
			//ȡ������
			rs.cancelRowUpdates();
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
