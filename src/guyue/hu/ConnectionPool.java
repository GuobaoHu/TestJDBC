package guyue.hu;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.concurrent.Executor;

public class ConnectionPool {
	/**
	 * �������װ����MyConn,�˴�ֱ����Connection,����ΪMyConnʵ����Connection�ӿ�
	 */
	private List<Connection> conns;
	
	/**
	 * ��ʼ�����ӳأ��������100�����ӵ����ӳ���
	 */
	public void initialConns() {
	}
	
	/**
	 * �������ݿ����ӳ�,���л�ȡһ��busyΪfalse�����ݿ����ӣ�������ж�æ���ͽ���һ���µ�������Connection
	 * @return ����1������
	 */
	public Connection getConn() {
		return null;
	}
	
	/**
	 * ʹ�ô���ģʽ��Connection������ģʽ���������ڿ��Լ���һЩ�����Լ��Ķ���
	 * �������廹�������̵Ĳ�Ʒ�����籾���������ݿ�����������
	 * @author hgb22613
	 *
	 */
	private class MyConn implements Connection {
		//�Ƿ��Ѿ���ռ��,�ı��ֵ��ʱ��ע���߳�ͬ��������
		boolean busy = false;
		
		//��ȡ��ǰʱ����Ϊ���ӵĿ�ʼ������������ʱ����,�ж������Ƿ�����쳣
		Date startTime = new Date();
		
		//���������ݿ������,MyConn����Ϊ��conn�Ĵ�������������Ĵ���ģʽ
		Connection conn;

		@Override
		public <T> T unwrap(Class<T> iface) throws SQLException {
			//�������������ݿ�����ӵ���Ӧ����������ģʽ
			return conn.unwrap(iface);
		}

		@Override
		public boolean isWrapperFor(Class<?> iface) throws SQLException {
			return conn.isWrapperFor(iface);
		}

		@Override
		public Statement createStatement() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String sql) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public CallableStatement prepareCall(String sql) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String nativeSQL(String sql) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setAutoCommit(boolean autoCommit) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean getAutoCommit() throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void commit() throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void rollback() throws SQLException {
			// TODO Auto-generated method stub
			
		}

		/**
		 * �˴�ע�ⲻ��ֱ�ӵ���conn�Ĺرշ��������ǽ�busy����Ϊfalse���������������ӳ����ٴο���
		 */
		public void close() throws SQLException {
			MyConn.this.busy = false;
		}

		@Override
		public boolean isClosed() throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public DatabaseMetaData getMetaData() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setReadOnly(boolean readOnly) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isReadOnly() throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setCatalog(String catalog) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getCatalog() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setTransactionIsolation(int level) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getTransactionIsolation() throws SQLException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public SQLWarning getWarnings() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void clearWarnings() throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Map<String, Class<?>> getTypeMap() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setHoldability(int holdability) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getHoldability() throws SQLException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Savepoint setSavepoint() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Savepoint setSavepoint(String name) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void rollback(Savepoint savepoint) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void releaseSavepoint(Savepoint savepoint) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
				throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Clob createClob() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Blob createBlob() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public NClob createNClob() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public SQLXML createSQLXML() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isValid(int timeout) throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setClientInfo(String name, String value) throws SQLClientInfoException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setClientInfo(Properties properties) throws SQLClientInfoException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getClientInfo(String name) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Properties getClientInfo() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setSchema(String schema) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getSchema() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void abort(Executor executor) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getNetworkTimeout() throws SQLException {
			// TODO Auto-generated method stub
			return 0;
		}
	}
	
	/**
	 * �ػ��̣߳���ÿ��30s����һ���̳߳أ�����Щ�߳̿�ʼ��5���ӻ�û�������ϣ���ȥ����Щ�쳣�̣߳�Ȼ�����¼���һ���µ�
	 */
	private class DeamonThread implements Runnable {

		@Override
		public void run() {
			
		}
		
	}
	
	public static void main(String[] args) {

	}

}
