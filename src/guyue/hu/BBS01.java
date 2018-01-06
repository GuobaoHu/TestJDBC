package guyue.hu;

import java.sql.*;
/**
 * 递归打印bbs项目，并缩进
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
	 * 在本方法中建立与数据库的连接
	 * 由于递归方法会多次调用，不适合在递归方法中连接数据库，否则会多次连接数据库，严重影响效率 
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
	 * 递归方法，求得父级下的所有子级
	 * @param pid 父id，本方法求得pid下的所有子项
	 * @param count 层级
	 */
	public void launch(Connection conn, int pid, int count) {
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			String sql = "select * from article where pid = ?";
			stm = conn.prepareStatement(sql);
			stm.setInt(1, pid);
			rs = stm.executeQuery();
			//缩进
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
