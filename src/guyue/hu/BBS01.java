package guyue.hu;

import java.sql.*;
/**
 * 递归打印bbs项目，并缩进
 * @author hgb22613
 *
 */
public class BBS01 {
	public static void main(String[] args) {
		new BBS01().launch(0, 0);
	}
	
	public void launch(int pid, int count) {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost/bbs?user=root&password=root";
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url);
			String sql = "select * from article where pid = ?";
			stm = conn.prepareStatement(sql);
			stm.setInt(1, pid);
			rs = stm.executeQuery();
			//缩进
			String prefix = "";
			for(int i=0; i<count; i++) {
				prefix = prefix + "    ";
			}
			
			while(rs.next()) {
				System.out.println(prefix + rs.getString(5));
				if(rs.getInt(7) == 1) {
					launch(rs.getInt(1), count + 1);
				}
			}
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
