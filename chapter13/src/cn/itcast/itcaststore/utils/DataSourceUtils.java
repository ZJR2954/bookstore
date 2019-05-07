package cn.itcast.itcaststore.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


//数据库工具类，提供获取连接对象度方法，提供获取数据源对象的方法
public class DataSourceUtils {
	private static DataSource dataSource=new ComboPooledDataSource();
	//保证事务里面用的是同一个数据源对象
	private static ThreadLocal<Connection> tl=new ThreadLocal<Connection>();
	
	public static DataSource getDataSource() {
		return dataSource;
	}
	public static Connection getConnection() throws SQLException{
		Connection con=tl.get();
		if(con==null) {
			con=dataSource.getConnection();
			tl.set(con);
		}
		return con;
	}
	public static void startTransaction() throws SQLException {
		Connection con=getConnection();
		if(con!=null) {
			con.setAutoCommit(false);
		}
	}
	public static void releaseAndCloseConnection() throws SQLException {
		Connection con=getConnection();
		if(con!=null) {
			con.commit();
			tl.remove();
			con.close();
		}
	}
	public static void rollback() throws SQLException {
		Connection con=getConnection();
		if(con != null) {
			con.rollback();
		}
	}
}
