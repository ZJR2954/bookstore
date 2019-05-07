package cn.itcast.itcaststore.service;

import java.sql.SQLException;

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

}
