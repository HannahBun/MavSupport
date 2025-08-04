package SeniorSupportHub;
import java.util.*;

//display phone number with rest of info when listing
//Make a way to edit every aspect of volunteer,senior, or hubtask, 
//make better looking display when listing data, 

//EXTRA IF TIME
//add verification of data like make sure phone number is a phone number, a email an email and so on
//make data entry more strict, like days available you can only type in days of week and time must be in certain format, that way, can make matchmaking system for volunteer availability and task time/day

public class SeniorSupportHub {
	private static Scanner scanner = new Scanner(System.in);
	private static List<Senior> seniors = new ArrayList<>();
    private static List<Volunteer> volunteers = new ArrayList<>();
    private static List<HubTask> tasks = new ArrayList<>();
	
	public static void main(String[] args) {
		loadAllData();

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1: manageSeniors(); break;
                case 2: manageVolunteers(); break;
                case 3: manageTasks(); break;
                case 4: running = false; break;
                default:
                    System.out.println("Invalid choice");
            }
        }

        saveAllData();
        System.out.println("Goodbye! Data saved.");
	}
	//====== Main menu ======
    private static void printMainMenu() {
        System.out.println("\n--- Senior Support Hub ---");
        System.out.println("1. Manage Seniors");
        System.out.println("2. Manage Volunteers");
        System.out.println("3. Manage HubTasks");
        System.out.println("4. Exit");
    }

    // ====== Seniors ======
    private static void manageSeniors() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Manage Seniors ---");
            System.out.println("1. List Seniors");
            System.out.println("2. Add Senior");
            System.out.println("3. Delete Senior");
            System.out.println("4. Back");
            int choice = readInt("Choice: ");
            switch (choice) {
                case 1: listSeniors(); break;
                case 2: addSenior(); break;
                case 3: deleteSenior(); break;
                case 4: back = true; break;
                default: System.out.println("Invalid choice");
            }
        }
    }

    private static void listSeniors() {
        if (seniors.isEmpty()) {
            System.out.println("No seniors found.");
            return;
        }
        System.out.println("Seniors:");
        for (int i = 0; i < seniors.size(); i++) {
            Senior s = seniors.get(i);
            System.out.printf("%d: %s, Age: %d, Email: %s\n", i, s.getName(), s.getAge(), s.getEmail());
        }
    }

    private static void addSenior() {
        System.out.println("Enter Senior details:");
        String name = readString("Name: ");
        String number = readString("Number: ");
        String email = readString("Email: ");
        String bio = readString("Bio: ");
        int age = readInt("Age: ");
        Senior s = new Senior(name, number, email, bio, age);
        seniors.add(s);
        System.out.println("Senior added.");
    }

    private static void deleteSenior() {
        listSeniors();
        if (seniors.isEmpty()) return;
        int idx = readInt("Enter index to delete: ");
        if (idx >= 0 && idx < seniors.size()) {
            seniors.remove(idx);
            System.out.println("Senior deleted.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    //====== Volunteers ======
    private static void manageVolunteers() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Manage Volunteers ---");
            System.out.println("1. List Volunteers");
            System.out.println("2. Add Volunteer");
            System.out.println("3. Delete Volunteer");
            System.out.println("4. Back");
            int choice = readInt("Choice: ");
            switch (choice) {
                case 1: listVolunteers(); break;
                case 2: addVolunteer(); break;
                case 3: deleteVolunteer(); break;
                case 4: back = true; break;
                default: System.out.println("Invalid choice");
            }
        }
    }

    private static void listVolunteers() {
        if (volunteers.isEmpty()) {
            System.out.println("No volunteers found.");
            return;
        }
        System.out.println("Volunteers:");
        for (int i = 0; i < volunteers.size(); i++) {
            Volunteer v = volunteers.get(i);
            System.out.printf("%d: %s, Email: %s, Days Available: %s\n", i, v.getName(), v.getEmail(), String.join(", ", v.getDaysAvailable()));
        }
    }

    private static void addVolunteer() {
        System.out.println("Enter Volunteer details:");
        String name = readString("Name: ");
        String number = readString("Number: ");
        String email = readString("Email: ");
        String bio = readString("Bio: ");
        String daysStr = readString("Days Available (comma separated): ");
        String[] daysAvailable = daysStr.split("\\s*,\\s*");
        Volunteer v = new Volunteer(name, number, email, bio, daysAvailable);
        volunteers.add(v);
        System.out.println("Volunteer added.");
    }

    private static void deleteVolunteer() {
        listVolunteers();
        if (volunteers.isEmpty()) return;
        int idx = readInt("Enter index to delete: ");
        if (idx >= 0 && idx < volunteers.size()) {
            volunteers.remove(idx);
            System.out.println("Volunteer deleted.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    //====== HubTasks ======
    private static void manageTasks() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Manage HubTasks ---");
            System.out.println("1. List HubTasks");
            System.out.println("2. Add HubTask");
            System.out.println("3. Delete HubTask");
            System.out.println("4. Back");
            int choice = readInt("Choice: ");
            switch (choice) {
                case 1: listTasks(); break;
                case 2: addTask(); break;
                case 3: deleteTask(); break;
                case 4: back = true; break;
                default: System.out.println("Invalid choice");
            }
        }
    }

    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        System.out.println("HubTasks:");
        for (int i = 0; i < tasks.size(); i++) {
            HubTask t = tasks.get(i);
            System.out.printf("%d: %s at %s on %s %s\n   Senior: %s, Volunteer: %s\n", i,
                    t.getDescription(), t.getLocation(), t.getDay(), t.getTime(),
                    t.getSenior().getName(), t.getVolunteer().getName());
        }
    }

    private static void addTask() {
        System.out.println("Enter HubTask details:");
        String description = readString("Description: ");
        String location = readString("Location: ");
        String day = readString("Day: ");
        String time = readString("Time: ");

        if (seniors.isEmpty()) {
            System.out.println("No seniors available! Add seniors first.");
            return;
        }
        if (volunteers.isEmpty()) {
            System.out.println("No volunteers available! Add volunteers first.");
            return;
        }

        listSeniors();
        int seniorIdx = readInt("Select Senior index: ");
        if (seniorIdx < 0 || seniorIdx >= seniors.size()) {
            System.out.println("Invalid senior index.");
            return;
        }
        Senior senior = seniors.get(seniorIdx);

        listVolunteers();
        int volIdx = readInt("Select Volunteer index: ");
        if (volIdx < 0 || volIdx >= volunteers.size()) {
            System.out.println("Invalid volunteer index.");
            return;
        }
        Volunteer volunteer = volunteers.get(volIdx);

        HubTask task = new HubTask(description, location, day, time, senior, volunteer);
        tasks.add(task);
        System.out.println("HubTask added.");
    }

    private static void deleteTask() {
        listTasks();
        if (tasks.isEmpty()) return;
        int idx = readInt("Enter index to delete: ");
        if (idx >= 0 && idx < tasks.size()) {
            tasks.remove(idx);
            System.out.println("Task deleted.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    //====== Load and Save ======

    private static void loadAllData() {
        seniors = Senior.loadAll();
        volunteers = Volunteer.loadAll();
        tasks = HubTask.loadAll(seniors, volunteers);
    }

    private static void saveAllData() {
        Senior.saveAll(seniors);
        Volunteer.saveAll(volunteers);
        HubTask.saveAll(tasks);
    }

    //====== Helpers ======

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
