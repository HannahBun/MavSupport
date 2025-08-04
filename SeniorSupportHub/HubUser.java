package SeniorSupportHub;

public class HubUser {
	protected String name;
	protected String number;
	protected String email;
	protected String bio; 
	
	public HubUser(String name, String number, String email, String bio) {
		this.name= name;
		this.number= number;
		this.email= email;
		this.bio = bio;
	}
	
	//getters
	public String getName() {
		return name;
	}

	public String getNumber() {
		return number;
	}

	public String getEmail() {
		return email;
	}

	public String getBio() {
		return bio;
	}

	//setters
	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
}
