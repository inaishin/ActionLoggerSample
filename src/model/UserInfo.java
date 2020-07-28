package model;

import java.io.Serializable;

//ユーザー情報のスコープ保存,JavaBeans
public class UserInfo implements Serializable {
	private String userId;
	private String pwdHash;
	private String name;
	private String address;
	private String tel;
	private String email;
	
	public UserInfo() {
		super();
	}

	// setter and getter
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPwdHash() {
		return pwdHash;
	}

	public void setPwdHash(String pwdHash) {
		this.pwdHash = pwdHash;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
