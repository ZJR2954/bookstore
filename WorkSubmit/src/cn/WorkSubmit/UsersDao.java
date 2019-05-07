package cn.WorkSubmit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UsersDao {
	public boolean insert(User user) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn=JDBCUtils.getConnection();
			stmt=conn.createStatement();
			String sql="INSERT INTO ws(id,username,email,is_push)"+
			"VALUES("
			+user.getId()
			+",'"
			+user.getUsername()
			+"','"
			+user.getEmail()
			+"',"
			+user.getIs_push()
			+")";
		int num = stmt.executeUpdate(sql);
		if(num>0) {
			return true;
		}
		return false;
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs,stmt,conn);
		}
		return false;
	}
	
	public ArrayList<User> findAll(){
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<User> list=new ArrayList<User>();
		try {
			conn=JDBCUtils.getConnection();
			stmt=conn.createStatement();
			String sql="SELECT * FROM ws";
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				User user=new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setIs_push(rs.getBoolean("is_push"));
				list.add(user);
			}
			return list;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, stmt,conn);
		}
		return null;
	}
	
	public static User find(int id) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn=JDBCUtils.getConnection();
			stmt=conn.createStatement();
			String sql="SELECT * FROM ws WHERE id="+id;
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				User user=new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setIs_push(rs.getBoolean("is_push"));
				return user;
			}
			return null;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs,stmt, conn);
		}
		return null;
	}
	
	public boolean delete(int id) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn=JDBCUtils.getConnection();
			stmt=conn.createStatement();
			String sql="DELETE FROM ws WHERE id="+id;
			int num=stmt.executeUpdate(sql);
			if(num>0) {
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs,stmt,conn);
		}
		return false;
	}
	
	public boolean update(User user) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn=JDBCUtils.getConnection();
			stmt=conn.createStatement();
			String sql="UPDATE ws set username='"+user.getUsername()
			+"',email='"+user.getEmail()
			+"',is_push="+user.getIs_push()
			+" WHERE id="+user.getId();
			int num=stmt.executeUpdate(sql);
			if(num>0) {
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs,stmt,conn);
		}
		return false;
	}

	public boolean updateIs_push(String username,boolean is_p) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn=JDBCUtils.getConnection();
			stmt=conn.createStatement();
			String sql="UPDATE ws set is_push="+is_p
			+" WHERE username='"+username+"'";
			int num=stmt.executeUpdate(sql);
			if(num>0) {
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs,stmt,conn);
		}
		return false;
	}
	
	public boolean findAllIs_push(){
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn=JDBCUtils.getConnection();
			stmt=conn.createStatement();
			String sql="SELECT * FROM ws";
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				if(rs.getBoolean("is_push")) {continue;}
				else {return false;}
			}
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, stmt,conn);
		}
		return false;
	}
	
	
}
