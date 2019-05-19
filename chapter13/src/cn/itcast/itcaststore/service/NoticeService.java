package cn.itcast.itcaststore.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.itcaststore.dao.NoticeDao;
import cn.itcast.itcaststore.domain.Notice;

public class NoticeService {
	private NoticeDao dao=new NoticeDao();
	
	public Notice getRecentNotice() {
		// TODO �Զ����ɵķ������
		try {
			return dao.getRecentNotice();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			throw new RuntimeException("��ѯ������ӻ���µ�һ������ʧ�ܣ�");
		}
	}

	public List<Notice> getAllNotices() {
		// TODO �Զ����ɵķ������
		List<Notice> list=null;
		try {
			list=dao.gtAllNotices();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			throw new RuntimeException("��ѯȫ������ʧ�ܣ�");
		}
		return list;
	}

	public void addNotice(Notice notice) {
		// TODO �Զ����ɵķ������
		try {
			dao.addNotice(notice);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			throw new RuntimeException("��ӹ���ʧ�ܣ�");
		}
	}

	public void deleteNotice(int n_id) {
		// TODO �Զ����ɵķ������
		try {
			dao.deleteNotice(n_id);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			throw new RuntimeException("ɾ������ʧ�ܣ�");
		}
	}

	public Notice findNoticeById(int n_id) {
		// TODO �Զ����ɵķ������
		Notice notice=null;
		try {
			notice=dao.findNoticeById(n_id);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			throw new RuntimeException("����ID��ѯ����ʧ�ܣ�");
		}
		return notice;
	}

	public void updateNotice(Notice notice) {
		// TODO �Զ����ɵķ������
		try {
			dao.updateNotice(notice);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			throw new RuntimeException("����ID�޸Ĺ���ʧ�ܣ�");
		}
	}

}
