package farmhub.login.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_login")
public class Login {
	@Id
private Long phoneNumber;
	@Column(name="email_id")
private String email;
private String password;
private String userName;

	public Login() {
		// TODO Auto-generated constructor stub
	}

	public Login(Long phoneNumber, String email, String password, String userName) {
		super();
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.userName = userName;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
