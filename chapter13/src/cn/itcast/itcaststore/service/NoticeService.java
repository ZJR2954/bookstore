package cn.itcast.itcaststore.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.itcaststore.dao.NoticeDao;
import cn.itcast.itcaststore.domain.Notice;

public class NoticeService {
	private NoticeDao dao=new NoticeDao();
	
	public Notice getRecentNotice() {
		// TODO 自动生成的方法存根
		try {
			return dao.getRecentNotice();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new RuntimeException("查询最新添加或更新的一条公告失败！");
		}
	}

	public List<Notice> getAllNotices() {
		// TODO 自动生成的方法存根
		List<Notice> list=null;
		try {
			list=dao.gtAllNotices();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new RuntimeException("查询全部公告失败！");
		}
		return list;
	}

	public void addNotice(Notice notice) {
		// TODO 自动生成的方法存根
		try {
			dao.addNotice(notice);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new RuntimeException("添加公告失败！");
		}
	}

	public void deleteNotice(int n_id) {
		// TODO 自动生成的方法存根
		try {
			dao.deleteNotice(n_id);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new RuntimeException("删除公告失败！");
		}
	}

	public Notice findNoticeById(int n_id) {
		// TODO 自动生成的方法存根
		Notice notice=null;
		try {
			notice=dao.findNoticeById(n_id);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new RuntimeException("根据ID查询公告失败！");
		}
		return notice;
	}

	public void updateNotice(Notice notice) {
		// TODO 自动生成的方法存根
		try {
			dao.updateNotice(notice);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new RuntimeException("根据ID修改公告失败！");
		}
	}

}
