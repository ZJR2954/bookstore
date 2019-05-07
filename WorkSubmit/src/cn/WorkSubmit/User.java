package cn.WorkSubmit;

public class User {
	private int id;
	private String username;
	private String email;
	private Boolean is_push=false;
	
	public void setId(int id) {
		this.id=id;
	}
	public int getId() {
		return id;
	}
	
	public void setUsername(String username) {
		this.username=username;
	}
	public String getUsername() {
		return username;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}
	public String getEmail() {
		return email;
	} 
	
	public void setIs_push(Boolean is_push) {
		this.is_push=is_push;
	}
	public Boolean getIs_push() {
		return is_push;
	}
}
