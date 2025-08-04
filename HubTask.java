package SeniorSupportHub;

public class HubTask {
	private String description;
	private String location;
	private String day;
	private String time;
	private Senior senior;
	private Volunteer volunteer;
	
	public HubTask(String description, String location, String day, String time, Senior senior, Volunteer volunteer) {
		this.description = description;
		this.location = location;
		this.day = day;
		this.time = time;
		this.senior = senior;
		this.volunteer = volunteer;
	}
	
	
}
