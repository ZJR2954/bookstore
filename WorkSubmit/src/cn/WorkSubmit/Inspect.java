package cn.WorkSubmit;

import java.util.ArrayList;

public class Inspect extends Thread{
	private Thread t;
	
	public void run() {			
		while(true){
			try {
				UsersDao usersDao = new UsersDao();
				ArrayList<User> list=usersDao.findAll();
				ArrayList<String> NoPushEmails=new ArrayList<String>();
				for(int i=0;i<list.size();i++) {
					if(list.get(i).getIs_push()==false) {
						NoPushEmails.add(list.get(i).getEmail());
					}
				}
					Thread.sleep(1000*60*60*5);
					for(String email : NoPushEmails) {
					MailUtils.sendMail(email, "你有任务未提交，如果一直不提交，将每5小时提醒一次！","");
					System.out.println("催任务成功");
					}		
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void start() {
		if(t==null) {
			t=new Thread(this);
			t.start();
		}
	}
}
