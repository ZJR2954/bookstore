package cn.WorkSubmit;


public class Test {	
		public static void main(String[] args) throws Exception {
//			UsersDao usersDao=new UsersDao();
//			ArrayList<User> list=usersDao.findAll();
//			ArrayList<String> NoPushEmails=new ArrayList<String>();
//			for(int i=0;i<list.size();i++) {
//				if(list.get(i).getIs_push()==false) {
//					NoPushEmails.add(list.get(i).getEmail());
//				}
//			}
//			while(true){
//						Thread.sleep(1000*5);
//						for(String email : NoPushEmails) {
//						MailUtils.sendMail(email, "你有任务未提交，如果一直不提交，将每5小时提醒一次！","");
//						System.out.println("催任务成功");
//						}
//			}
//			System.out.println("...");
//			Inspect T1 = new Inspect();
//			T1.start();
		
			MailUtils.sendMail("763305173@qq.com", "你有任务未提交，如果一直不提交，将每5小时提醒一次！","");
	}
}
