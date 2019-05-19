package domain;

/**
 *
 * @author dinjo998
 */
public class Customer {
	private String username;
	private String password;
	private String name;
	private String email;
	private String address;
	private String creditCard;

	public Customer(String username, String password, String name, String email, 
			  String address, String creditCard) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.address = address;
		this.creditCard = creditCard;
	}

	public Customer() {
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	@Override
	public String toString() {
		return "Customer{" + "username=" + username + ", name=" + name + '}';
	}
	
}
