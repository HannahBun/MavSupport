package SeniorSupportHub;
import java.io.*;
import java.util.*;
public class Volunteer extends HubUser implements HubFileInterface{
	private String[] daysAvailable;
	private static final String FILE_NAME = "VolunteerData.txt";
	
	public Volunteer(String name, String number, String email, String bio, String[] daysAvailable) {
		super(name, number, email, bio);
		this.daysAvailable= new String[daysAvailable.length];
		for(int i=0; i<daysAvailable.length;i++) {
			this.daysAvailable[i] = daysAvailable[i];
		}
	}
	
	public String[] getDaysAvailable() {
		return daysAvailable;
	}

	public void setDaysAvailable(String[] daysAvailable) {
		this.daysAvailable = new String[daysAvailable.length];
		for (int i = 0; i < daysAvailable.length; i++) {
			this.daysAvailable[i] = daysAvailable[i];
		}
	}
	
	//save volunteer data to file
	public void save() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME,true))) {
			// Convert daysAvailable array to a single string
			StringBuilder days = new StringBuilder();
			for (int i = 0; i < daysAvailable.length; i++) {
				days.append(daysAvailable[i]);
				if (i < daysAvailable.length - 1) {
					days.append(";");
				}
			}

			//save all data as one line: name,number,email,bio,days1;days2;days3
			writer.write(name + "," + number + "," + email + "," + bio + "," + days.toString());
			writer.newLine();

			System.out.println("Volunteer saved to " + FILE_NAME);
		} catch (IOException e) {
			System.out.println("Error saving volunteer: " + e.getMessage());
		}
	}

	@Override
	public String toDataString() {
	    StringBuilder days = new StringBuilder();
	    for (int i = 0; i < daysAvailable.length; i++) {
	        days.append(daysAvailable[i]);
	        if (i < daysAvailable.length - 1) days.append(";");
	    }
	    return name + "," + number + "," + email + "," + bio + "," + days.toString();
	}
	
	public static Volunteer fromDataString(String line) {
		String[] parts = line.split(",", 5);
		String name = parts[0];
		String number = parts[1];
		String email = parts[2];
		String bio = parts[3];
		String[] daysAvailable = parts[4].split(";");
		return new Volunteer(name, number, email, bio, daysAvailable);
	}
	
	public static List<Volunteer> loadAll() {
		List<Volunteer> volunteers = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
			String line;
			while ((line = reader.readLine()) != null) {
				volunteers.add(fromDataString(line));
			}
		} catch (IOException e) {
			System.out.println("Error loading volunteers: " + e.getMessage());
		}
		return volunteers;
	}

	public static void saveAll(List<Volunteer> volunteers) {
		try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
        } catch (IOException e) {
        	System.out.println("Error saving volunteers: " + e.getMessage());;
        }
		
		for (Volunteer v : volunteers) {
			v.save();
		}
	}
}
