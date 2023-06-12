package farmhub.login.dto;

import java.util.Objects;

import javax.validation.constraints.Pattern;

public class LoginDTO {
	@Pattern(regexp = "[4-9][0-9]{9}", message = "{login.invalid.phonenumber}")
	private Long phoneNumber;
	@Pattern(regexp = "[a-Z0-9]{15}", message = "{login.invalid.username}")
	private String userName;
@Pattern(regexp="([A-z0-9]}{3,})[@]([a-z]{3,7})[.](com|in)",message="login.invalid.email")
	private String email;
	@Pattern(regexp = "^.*(?=.{6,})(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!&$%&? \"]).*$",message = "{login.invalid.password}")
	private String password;

	public LoginDTO() {
		// TODO Auto-generated constructor stub
	}

	public LoginDTO(Long phoneNumber, String userName, String email, String password) {
		super();
		this.phoneNumber = phoneNumber;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	@Override
	public int hashCode() {
		return Objects.hash(email, password, phoneNumber, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginDTO other = (LoginDTO) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password)
				&& Objects.equals(phoneNumber, other.phoneNumber) && Objects.equals(userName, other.userName);
	}

	@Override
	public String toString() {
		return "LoginDTO [phoneNumber=" + phoneNumber + ", userName=" + userName + ", email=" + email + ", password="
				+ password + "]";
	}

}
