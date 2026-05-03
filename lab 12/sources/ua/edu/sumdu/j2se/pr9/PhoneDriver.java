package ua.edu.sumdu.j2se.pr9;

import java.util.Scanner;

public class PhoneDriver {
    private static Scanner scanner = new Scanner(System.in);
    private static Store myStore = new Store("Angie's Tech Store");
    private static DatabaseManager dbManager;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: Provide path to db.properties as first argument!");
            return;
        }

        dbManager = new DatabaseManager(args[0]);

        loadData();

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nMain Menu");
            System.out.println("1. Search object");      
            System.out.println("2. Create new object");  
            System.out.println("3. Show all objects");   
            System.out.println("4. Exit");      
            System.out.print("Your choice: ");
            
            String choice = scanner.nextLine();
            switch (choice) {
                case "1": searchMenu(); break; 
                case "2": creationSubMenu(); break;
                case "3": myStore.showAll(); break; 
                case "4": isRunning = false; break;
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
                    System.out.println("Error: Invalid input!");
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
            System.out.println("Error: Invalid input!");
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
                dbManager.savePhone(newPhone, qty);
            }
        } catch (Exception e) {
            System.out.println("Error: Invalid data format!");
        }
    }

    private static void loadData() {
        System.out.println("System initialized. Ready to work with PostgreSQL.");
    }
}