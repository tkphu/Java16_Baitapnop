package cybersoft.javabackend.gamedoanso.model;
/*
 * Mục đích: Lớp quản lý Email
 * Người tạo: Trần Kim Phú
 * Ngày tạo: 23/01/2022
 * Version: 1.0
 * */
public class Email {
	private String email;
	private String ePassword;

	public Email() {

	}

	public Email(String email, String ePassword) {
		super();
		this.email = email;
		this.ePassword = ePassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEPassword() {
		return ePassword;
	}

	public void setEPassword(String ePassword) {
		this.ePassword = ePassword;
	}

}
