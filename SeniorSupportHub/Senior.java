package SeniorSupportHub;

public class Senior extends HubUser{
	private int age;
	
	public Senior(String name, String number, String email, String bio, int age) {
		super(name, number, email, bio);
		this.age = age;
	}
}
