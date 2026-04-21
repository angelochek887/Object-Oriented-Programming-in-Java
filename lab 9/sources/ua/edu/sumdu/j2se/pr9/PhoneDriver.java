package ua.edu.sumdu.j2se.pr9;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PhoneDriver {
    private static final String FILE_NAME = "input.txt";
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Phone> deviceList = new ArrayList<>();

    public static void main(String[] args) {
        loadData();

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nFile Persistence");
            System.out.println("1. Create new object");
            System.out.println("2. Show all objects");
            System.out.println("3. Exit and Save");
            System.out.print("Your choice: ");
            
            String choice = scanner.nextLine();
            switch (choice) {
                case "1": creationSubMenu(); break;
                case "2": showAll(); break;
                case "3": saveData(); isRunning = false; break;
                default: System.out.println("Error: Choose 1, 2 or 3.");
            }
        }
    }

    private static void creationSubMenu() {
        System.out.println("\nChoose Type to Create");
        System.out.println("1. Base Phone\n2. SmartPhone\n3. KeypadPhone\n4. GamingPhone\n5. SatellitePhone\n6. Back");
        System.out.print("Enter type number (1-6): ");
        
        String typeChoice = scanner.nextLine();
        if (typeChoice.equals("6")) return;
        if (!typeChoice.matches("[1-5]")) {
            System.out.println("Error: Invalid input! You must enter a NUMBER (1-5).");
            return; 
        }

        try {
            System.out.print("Enter Brand: "); String b = scanner.nextLine();
            System.out.print("Enter Model: "); String m = scanner.nextLine();
            System.out.print("Enter Storage (GB): "); int s = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Price ($): "); double p = Double.parseDouble(scanner.nextLine());
            
            switch (typeChoice) {
                case "1": 
                    deviceList.add(new Phone(b, m, s, p, OsType.UNKNOWN)); 
                    break;
                case "2":
                    System.out.print("Enter Camera MP: "); int cam = Integer.parseInt(scanner.nextLine());
                    OsType smartOs = b.equalsIgnoreCase("Apple") ? OsType.IOS : OsType.ANDROID;
                    deviceList.add(new SmartPhone(b, m, s, p, smartOs, cam));
                    break;
                case "3":
                    System.out.print("Has Flashlight? (y/n): "); boolean f = scanner.nextLine().equalsIgnoreCase("y");
                    deviceList.add(new KeypadPhone(b, m, s, p, f));
                    break;
                case "4":
                    System.out.print("Enter Camera MP: "); int camG = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Cooling: "); String cool = scanner.nextLine();
                    deviceList.add(new GamingPhone(b, m, s, p, OsType.ANDROID, camG, cool));
                    break;
                case "5":
                    System.out.print("Enter Provider: "); String prov = scanner.nextLine();
                    deviceList.add(new SatellitePhone(b, m, s, p, prov));
                    break;
            }
            System.out.println("Success: Object added!");
        } catch (Exception e) {
            System.out.println("Error: Invalid data format! (Check Storage/Price/Numbers)");
        }
    }

    private static void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Phone p : deviceList) {
                writer.write(p.toFileString());
                writer.newLine();
            }
            System.out.println("Success: Data saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Save error: " + e.getMessage());
        }
    }

    private static void loadData() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String type = parts[0];
                String b = parts[1]; String m = parts[2];
                int s = Integer.parseInt(parts[3]);
                double p = Double.parseDouble(parts[4].replace(",", "."));
                OsType os = OsType.valueOf(parts[5]);

                switch (type) {
                    case "PHONE": deviceList.add(new Phone(b, m, s, p, os)); break;
                    case "SMARTPHONE": deviceList.add(new SmartPhone(b, m, s, p, os, Integer.parseInt(parts[6]))); break;
                    case "GAMINGPHONE": deviceList.add(new GamingPhone(b, m, s, p, os, Integer.parseInt(parts[6]), parts[7])); break;
                    case "KEYPADPHONE": deviceList.add(new KeypadPhone(b, m, s, p, parts[6].equals("true"))); break;
                    case "SATELLITEPHONE": deviceList.add(new SatellitePhone(b, m, s, p, parts[6])); break;
                }
            }
            System.out.println("Success: Data loaded from file.");
        } catch (Exception e) {
            System.out.println("Log: Initializing new collection.");
        }
    }

    private static void showAll() {
        if (deviceList.isEmpty()) System.out.println("Collection is empty.");
        else for (Phone p : deviceList) System.out.println(p.toString());
    }
}