package cn.itcast.itcaststore.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.itcaststore.domain.Notice;
import cn.itcast.itcaststore.utils.DataSourceUtils;

public class NoticeDao {

	public Notice getRecentNotice() throws SQLException {
		// TODO 自动生成的方法存根
		String sql="select * from notice order by n_time desc limit 0,1";
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<Notice>(Notice.class));
	}

	public List<Notice> gtAllNotices() throws SQLException {
		// TODO 自动生成的方法存根
		String sql="select * from notice order by n_time desc limit 0,10";
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Notice>(Notice.class));
	}

	public void addNotice(Notice notice) throws SQLException {
		// TODO 自动生成的方法存根
		String sql="insert into notice(title,details,n_time) values(?,?,?)";
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql,notice.getTitle(),notice.getDetails(),notice.getN_time());
	}

	public void deleteNotice(int n_id) throws SQLException {
		// TODO 自动生成的方法存根
		String sql="delete from notice where n_id=?";
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql,n_id);
	}

	public Notice findNoticeById(int n_id) throws SQLException {
		// TODO 自动生成的方法存根
		String sql="select * from notice where n_id=?";
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql,new BeanHandler<Notice>(Notice.class),n_id);
	}

	public void updateNotice(Notice notice) throws SQLException {
		// TODO 自动生成的方法存根
		String sql="update notice set title=?,details=?,n_time=? where n_id=?";
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql,notice.getTitle(),notice.getDetails(),notice.getN_time(),notice.getN_id());
	}

}
