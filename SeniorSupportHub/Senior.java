package SeniorSupportHub;
import java.io.*;
import java.util.*;
public class Senior extends HubUser implements HubFileInterface{
	private int age;
	private static final String FILE_NAME = "SeniorData.txt";
	
	public Senior(String name, String number, String email, String bio, int age) {
		super(name, number, email, bio);
		this.age = age;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public void save() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME,true))) {
			writer.write(name + "," + number + "," + email + "," + bio + "," + age);
			writer.newLine();
			//System.out.println("Senior saved to " + FILE_NAME);
		} catch (IOException e) {
			System.out.println("Error saving senior: " + e.getMessage());
		}
	}
	
	@Override
	public String toDataString() {
	    return name + "," + number + "," + email + "," + bio + "," + age;
	}
	
	public static Senior fromDataString(String line) {
		String[] parts = line.split(",", 5);
		String name = parts[0];
		String number = parts[1];
		String email = parts[2];
		String bio = parts[3];
		int age = Integer.parseInt(parts[4]);
		return new Senior(name, number, email, bio, age);
	}
	
	public static void saveAll(List<Senior> seniors) {
		try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
        } catch (IOException e) {
        	System.out.println("Error saving seniors: " + e.getMessage());;
        }
		for (Senior s : seniors) {
			s.save();
		}
	}
	
	public static List<Senior> loadAll() {
		List<Senior> seniors = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
			String line;
			while ((line = reader.readLine()) != null) {
				seniors.add(fromDataString(line));
			}
		} catch (IOException e) {
			System.out.println("Error loading seniors: " + e.getMessage());
		}
		return seniors;
	}
}
