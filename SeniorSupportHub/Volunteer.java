package SeniorSupportHub;

public class Volunteer extends HubUser{
	private String[] daysAvailable;
	
	public Volunteer(String name, String number, String email, String bio, String[] daysAvailable) {
		super(name, number, email, bio);
		this.daysAvailable= new int[daysAvailable.length];
		for(int i=0; i<daysAvailable.length;i++) {
			this.daysAvailable[i] = daysAvailable[i];
		}
	}
	
	
}
