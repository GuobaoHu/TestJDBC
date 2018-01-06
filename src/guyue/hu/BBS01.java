package guyue.hu;

import java.sql.*;
/**
 * �ݹ��ӡbbs��Ŀ��������
 * @author hgb22613
 *
 */
public class BBS01 {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/bbs?user=root&password=root";
	
	public static void main(String[] args) {
		new BBS01().show();
	}
	
	/**
	 * �ڱ������н��������ݿ������
	 * ���ڵݹ鷽�����ε��ã����ʺ��ڵݹ鷽�����������ݿ⣬��������������ݿ⣬����Ӱ��Ч�� 
	 */
	public void show() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url);
			launch(conn, 0, 0);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * �ݹ鷽������ø����µ������Ӽ�
	 * @param pid ��id�����������pid�µ���������
	 * @param count �㼶
	 */
	public void launch(Connection conn, int pid, int count) {
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			String sql = "select * from article where pid = ?";
			stm = conn.prepareStatement(sql);
			stm.setInt(1, pid);
			rs = stm.executeQuery();
			//����
			StringBuffer prefix = new StringBuffer("");
			for(int i=0; i<count; i++) {
				prefix.append("    ");
			}
			
			while(rs.next()) {
				System.out.println(prefix + rs.getString(5));
				if(rs.getInt(7) == 1) {
					launch(conn, rs.getInt(1), count + 1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(stm != null) stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*public void printf(ResultSet rs) {
		try {
			while(rs.next()) {
				System.out.println(rs.getString(5));
				if(rs.getInt(7) == 1)
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/

}
