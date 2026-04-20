package ua.edu.sumdu.j2se.pr7;

import java.util.ArrayList;
import java.util.Scanner;

public class PhoneDriver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Phone> deviceList = new ArrayList<>();
        
        System.out.println(" Inheritance & Polymorphism ");

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n1. Create SmartPhone");
            System.out.println("2. Create KeypadPhone");
            System.out.println("3. Show all devices (Polymorphism test)");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");
            
            String choice = scanner.nextLine();
            try {
                switch (choice) {
                    case "1":
                        System.out.print("Brand: "); String b1 = scanner.nextLine();
                        System.out.print("Model: "); String m1 = scanner.nextLine();
                        System.out.print("OS (1-Android, 2-iOS): ");
                        OsType os = scanner.nextLine().equals("2") ? OsType.IOS : OsType.ANDROID;
                        System.out.print("Camera MP: "); 
                        int cam = Integer.parseInt(scanner.nextLine());
                        deviceList.add(new SmartPhone(b1, m1, 128, 999.0, os, cam));
                        break;
                    case "2":
                        System.out.print("Brand: "); String b2 = scanner.nextLine();
                        System.out.print("Model: "); String m2 = scanner.nextLine();
                        System.out.print("Flashlight (y/n): ");
                        boolean flash = scanner.nextLine().equalsIgnoreCase("y");
                        deviceList.add(new KeypadPhone(b2, m2, 1, 49.0, flash));
                        break;
                    case "3":
                        System.out.println("\n--- Inventory List ---");
                        for (int i = 0; i < deviceList.size(); i++) {
                            System.out.println(deviceList.get(i).toString());
                        }
                        break;
                    case "4":
                        isRunning = false;
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}