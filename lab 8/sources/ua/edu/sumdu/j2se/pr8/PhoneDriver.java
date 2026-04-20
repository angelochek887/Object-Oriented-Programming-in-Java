package ua.edu.sumdu.j2se.pr8;

import java.util.ArrayList;
import java.util.Scanner;

public class PhoneDriver {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Phone> deviceList = new ArrayList<>();

    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n Hierarchy Management ");
            System.out.println("1. Create new object");
            System.out.println("2. Show all objects");
            System.out.println("3. Exit");
            System.out.print("Your choice: ");
            
            String choice = scanner.nextLine();
            switch (choice) {
                case "1": creationSubMenu(); break;
                case "2": showAll(); break;
                case "3": isRunning = false; break;
                default: System.out.println("Error: Please choose 1, 2 or 3.");
            }
        }
    }

    private static void creationSubMenu() {
        System.out.println("\n--- Choose Type to Create ---");
        System.out.println("1. Base Phone");
        System.out.println("2. SmartPhone");
        System.out.println("3. KeypadPhone");
        System.out.println("4. GamingPhone");
        System.out.println("5. SatellitePhone");
        System.out.println("6. Back to main menu");
        System.out.print("Enter type number (1-6): ");
        
        String type = scanner.nextLine();

        if (type.equals("6")) return;
        if (!type.matches("[1-5]")) {
            System.out.println("Error: Invalid type! Please enter a NUMBER between 1 and 5.");
            return;
        }

        try {
            System.out.print("Enter Brand: "); String b = scanner.nextLine();
            System.out.print("Enter Model: "); String m = scanner.nextLine();
            System.out.print("Enter Price: "); double p = Double.parseDouble(scanner.nextLine());
            
            switch (type) {
                case "1": 
                    deviceList.add(new Phone(b, m, 64, p, OsType.UNKNOWN)); 
                    break;
                case "2":
                    System.out.print("Enter Camera MP: "); 
                    int cam = Integer.parseInt(scanner.nextLine());
                    deviceList.add(new SmartPhone(b, m, 128, p, OsType.ANDROID, cam));
                    break;
                case "3":
                    System.out.print("Has Flashlight? (y/n): "); 
                    boolean f = scanner.nextLine().equalsIgnoreCase("y");
                    deviceList.add(new KeypadPhone(b, m, 1, p, f));
                    break;
                case "4":
                    System.out.print("Enter Cooling system: "); 
                    String cool = scanner.nextLine();
                    deviceList.add(new GamingPhone(b, m, 512, p, OsType.ANDROID, 108, cool));
                    break;
                case "5":
                    System.out.print("Enter Satellite Provider: "); 
                    String prov = scanner.nextLine();
                    deviceList.add(new SatellitePhone(b, m, 4, p, prov));
                    break;
            }
            System.out.println("Success: Object added to collection!");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format! Please use numbers for Price and Attributes.");
        }
    }

    private static void showAll() {
        System.out.println("\n--- Current Device Collection ---");
        if (deviceList.isEmpty()) {
            System.out.println("The collection is currently empty.");
        } else {
            for (Phone p : deviceList) {
                System.out.println(p.toString());
            }
        }
    }
}