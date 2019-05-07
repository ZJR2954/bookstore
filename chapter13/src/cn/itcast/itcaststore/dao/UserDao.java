package cn.itcast.itcaststore.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.utils.DataSourceUtils;

public class UserDao {

	public void addUser(User user) throws SQLException {
		SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql="insert into user(username,password,gender,email,telephone,introduce,activeCode,registTime) values(?,?,?,?,?,?,?,?)";
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		int row=runner.update(sql,user.getUsername(),user.getPassword(),user.getGender(),
								  user.getEmail(),user.getTelephone(),user.getIntroduce(),
								  user.getActiveCode(),ft.format(user.getRegistTime())
							  );
		if(row==0) {
			throw new RuntimeException();
		}
	}

	public User findUserByActiveCode(String activeCode) throws SQLException {
		String sql="select * from user where activeCode=?";
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		User user=runner.query(sql,new BeanHandler<User>(User.class),activeCode);
		return user;
	}
	public void activeUser(String activeCode) throws SQLException {
		String sql="update user set state=? where activeCode=?";
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		int row=runner.update(sql,1,activeCode);
		if(row==0) {
			throw new RuntimeException();
		}
	}

	public User findUserByUsername(String username) throws SQLException {
		String sql="select * from user where username=?";
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		User user=runner.query(sql,new BeanHandler<User>(User.class),username);
		return user;
	}
	public User findUserByUsernameAndPassword(String username, String password) throws SQLException {
		String sql="select * from user where username=? and password=?";
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		User user=runner.query(sql,new BeanHandler<User>(User.class),username,password);
		return user;
	}

}
