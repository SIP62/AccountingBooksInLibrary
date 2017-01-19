package by.bsuir.lab01.entity;

public class User {
	private int id;
	private String login;
	private String password;
	private String status;
	private String eMail;
	public User(int id, String login, String password, String status, String eMail) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.status = status;
		this.eMail = eMail;
	}
	public int getId() {
		return id;
	}
	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}
	public String getStatus() {
		return status;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	@Override
	public int hashCode() {
		int result = id + ((login == null) ? 0 : login.hashCode());
		result = 31 * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		id = other.id;
		status = other.status;
		return true;
	}
	@Override
	public String toString() {
	return "User [id=" + id + ", login=" + login + ", password=" + password + ", status=" + status + "]";
	}	

}
