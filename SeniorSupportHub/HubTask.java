package SeniorSupportHub;
import java.io.*;
import java.util.*;
public class HubTask implements HubFileInterface{
	private String description;
	private String location;
	private String day;
	private String time;
	private Senior senior;
	private Volunteer volunteer;
	
	private static final String FILE_NAME = "HubTaskData.txt";
	
	public HubTask(String description, String location, String day, String time, Senior senior, Volunteer volunteer) {
		this.description = description;
		this.location = location;
		this.day = day;
		this.time = time;
		this.senior = senior;
		this.volunteer = volunteer;
	}
	
	//getters
	public String getDescription() { return description; }
	public String getLocation() { return location; }
	public String getDay() { return day; }
	public String getTime() { return time; }
	public Senior getSenior() { return senior; }
	public Volunteer getVolunteer() { return volunteer; }

	//setters
	public void setDescription(String description) { this.description = description; }
	public void setLocation(String location) { this.location = location; }
	public void setDay(String day) { this.day = day; }
	public void setTime(String time) { this.time = time; }
	public void setSenior(Senior senior) { this.senior = senior; }
	public void setVolunteer(Volunteer volunteer) { this.volunteer = volunteer; }
	
	@Override
	public void save() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME,true))) {
			writer.write(description + "|" + location + "|" + day + "|" + time + "|" +
				senior.toDataString() + "|" + volunteer.toDataString());
			writer.newLine();
			System.out.println("HubTask saved to " + FILE_NAME);
		} catch (IOException e) {
			System.out.println("Error saving task: " + e.getMessage());
		}
	}

	public String toDataString() {
		return description + "|" + location + "|" + day + "|" + time + "|" +
				senior.getName() + "|" + volunteer.getName();
	}

	//load all tasks need seniors and volunteers list to match names
	public static List<HubTask> loadAll(List<Senior> seniors, List<Volunteer> volunteers) {
		List<HubTask> tasks = new ArrayList<>();
		File file = new File(FILE_NAME);
		if (!file.exists()) return tasks;

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("\\|", 6);
				if (parts.length == 6) {
					String description = parts[0];
					String location = parts[1];
					String day = parts[2];
					String time = parts[3];
					String seniorName = parts[4];
					String volunteerName = parts[5];

					Senior matchedSenior = findSeniorByName(seniors, seniorName);
					Volunteer matchedVolunteer = findVolunteerByName(volunteers, volunteerName);

					if (matchedSenior != null && matchedVolunteer != null) {
						tasks.add(new HubTask(description, location, day, time, matchedSenior, matchedVolunteer));
					} else {
						System.out.println("Warning: Skipping task due to missing senior or volunteer: " + description);
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Error loading tasks: " + e.getMessage());
		}
		return tasks;
	}

	public static void saveAll(List<HubTask> tasks) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
			for (HubTask t : tasks) {
				writer.write(t.toDataString());
				writer.newLine();
			}
		} catch (IOException e) {
			System.out.println("Error saving tasks: " + e.getMessage());
		}
	}

	private static Senior findSeniorByName(List<Senior> seniors, String name) {
		for (Senior s : seniors) {
			if (s.getName().equals(name)) return s;
		}
		return null;
	}

	private static Volunteer findVolunteerByName(List<Volunteer> volunteers, String name) {
		for (Volunteer v : volunteers) {
			if (v.getName().equals(name)) return v;
		}
		return null;
	}
}
