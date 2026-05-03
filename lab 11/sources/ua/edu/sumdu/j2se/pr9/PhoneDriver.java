package ua.edu.sumdu.j2se.pr9;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PhoneDriver {
    private static final String FILE_NAME = "input.txt";
    private static Scanner scanner = new Scanner(System.in);
    
    private static Store myStore = new Store("Angie's Tech Store");

    public static void main(String[] args) {
        loadData();

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nMain Menu");
            System.out.println("1. Search object");      
            System.out.println("2. Create new object");  
            System.out.println("3. Show all objects");   
            System.out.println("4. Exit and Save");      
            System.out.print("Your choice: ");
            
            String choice = scanner.nextLine();
            switch (choice) {
                case "1": searchMenu(); break; 
                case "2": creationSubMenu(); break;
                case "3": myStore.showAll(); break; 
                case "4": saveData(); isRunning = false; break;
                default: System.out.println("Error: Choose 1, 2, 3 or 4.");
            }
        }
    }

    private static void searchMenu() {
        boolean searching = true;
        while (searching) {
            System.out.println("\nSearch Menu ");
            System.out.println("1. Search by Brand");
            System.out.println("2. Search by Maximum Price");
            System.out.println("3. Search by Minimum Storage (GB)");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose search criteria (1-4): ");

            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    System.out.print("Enter Brand to search: ");
                    myStore.searchByBrand(scanner.nextLine());
                    break;
                case "2":
                    System.out.print("Enter Maximum Price ($): ");
                    try {
                        myStore.searchByMaxPrice(Double.parseDouble(scanner.nextLine()));
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid price format.");
                    }
                    break;
                case "3":
                    System.out.print("Enter Minimum Storage (GB): ");
                    try {
                        myStore.searchByMinStorage(Integer.parseInt(scanner.nextLine()));
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid storage format.");
                    }
                    break;
                case "4":
                    searching = false;
                    break;
                default:
                    System.out.println("Error: Invalid input! Enter a number between 1 and 4.");
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
            System.out.print("Enter Quantity: "); int qty = Integer.parseInt(scanner.nextLine());

            Phone newPhone = null;

            switch (typeChoice) {
                case "1": 
                    newPhone = new Phone(b, m, s, p, OsType.UNKNOWN); 
                    break;
                case "2":
                    System.out.print("Enter Camera MP: "); int cam = Integer.parseInt(scanner.nextLine());
                    OsType smartOs = b.equalsIgnoreCase("Apple") ? OsType.IOS : OsType.ANDROID;
                    newPhone = new SmartPhone(b, m, s, p, smartOs, cam);
                    break;
                case "3":
                    System.out.print("Has Flashlight? (y/n): "); boolean f = scanner.nextLine().equalsIgnoreCase("y");
                    newPhone = new KeypadPhone(b, m, s, p, f);
                    break;
                case "4":
                    System.out.print("Enter Camera MP: "); int camG = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Cooling: "); String cool = scanner.nextLine();
                    newPhone = new GamingPhone(b, m, s, p, OsType.ANDROID, camG, cool);
                    break;
                case "5":
                    System.out.print("Enter Provider: "); String prov = scanner.nextLine();
                    newPhone = new SatellitePhone(b, m, s, p, prov);
                    break;
            }

            if (newPhone != null) {
                myStore.addNewPhone(newPhone, qty);
            }
        } catch (Exception e) {
            System.out.println("Error: Invalid data format! (Check Storage/Price/Numbers)");
        }
    }

    private static void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            ArrayList<Phone> phones = myStore.getPhones();
            ArrayList<Integer> qtys = myStore.getQuantities();
            
            for (int i = 0; i < phones.size(); i++) {
                writer.write(phones.get(i).toFileString() + "|" + qtys.get(i));
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
            System.out.println("Loading inventory...");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String type = parts[0];
                String b = parts[1]; String m = parts[2];
                int s = Integer.parseInt(parts[3]);
                double p = Double.parseDouble(parts[4].replace(",", "."));
                OsType os = OsType.valueOf(parts[5]);

                int qty = 1;
                try {
                    qty = Integer.parseInt(parts[parts.length - 1]);
                } catch (NumberFormatException e) {
                    qty = 1; 
                }

                Phone phoneToAdd = null;

                switch (type) {
                    case "PHONE": phoneToAdd = new Phone(b, m, s, p, os); break;
                    case "SMARTPHONE": phoneToAdd = new SmartPhone(b, m, s, p, os, Integer.parseInt(parts[6])); break;
                    case "GAMINGPHONE": phoneToAdd = new GamingPhone(b, m, s, p, os, Integer.parseInt(parts[6]), parts[7]); break;
                    case "KEYPADPHONE": phoneToAdd = new KeypadPhone(b, m, s, p, parts[6].equals("true")); break;
                    case "SATELLITEPHONE": phoneToAdd = new SatellitePhone(b, m, s, p, parts[6]); break;
                }

                if (phoneToAdd != null) {
                    myStore.addNewPhone(phoneToAdd, qty);
                }
            }
            System.out.println("Success: Data loaded from file.");
        } catch (Exception e) {
            System.out.println("Log: Initializing new collection.");
        }
    }
}