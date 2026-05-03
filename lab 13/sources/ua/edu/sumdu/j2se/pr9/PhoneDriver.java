package ua.edu.sumdu.j2se.pr9;

import java.util.Scanner;

public class PhoneDriver {
    public static void main(String[] args) {
        Store store = new Store();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nMain Menu");
            System.out.println("1. Search object");
            System.out.println("2. Create new object");
            System.out.println("3. Show all objects");
            System.out.println("4. Вивести відсортовану інформацію про всі об'єкти");
            System.out.println("5. Exit");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter brand to search: ");
                    String searchBrand = scanner.nextLine();
                    store.searchByBrand(searchBrand);
                    break;
                case 2:
                    System.out.println("\nChoose Type to Create:");
                    System.out.println("1. SmartPhone");
                    System.out.println("2. KeypadPhone");
                    System.out.println("3. Back");
                    System.out.print("Enter type number (1-3): ");
                    
                    int type = scanner.nextInt();
                    scanner.nextLine();

                    if (type == 3) break;

                    System.out.print("Enter Brand: ");
                    String brand = scanner.nextLine();
                    System.out.print("Enter Model: ");
                    String model = scanner.nextLine();
                    System.out.print("Enter Storage (GB): ");
                    int storage = scanner.nextInt();
                    System.out.print("Enter Price ($): ");
                    double price = scanner.nextDouble();

                    if (type == 1) {
                        System.out.print("Enter Camera MP: ");
                        int cameraMp = scanner.nextInt();
                        store.addPhone(new SmartPhone(brand, model, storage, price, cameraMp));
                        System.out.println("-> SmartPhone successfully added!");
                    } else if (type == 2) {
                        System.out.print("Has flashlight? (true/false): ");
                        boolean hasFlash = scanner.nextBoolean();
                        store.addPhone(new KeypadPhone(brand, model, storage, price, hasFlash));
                        System.out.println("-> KeypadPhone successfully added!");
                    } else {
                        System.out.println("Invalid type selected.");
                    }
                    break;
                case 3:
                    store.showAllPhones();
                    break;
                case 4:
                    store.showSortedPhones();
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    running = false;
                    break;
                default:
                    System.out.println("Wrong choice! Please enter a number from 1 to 5.");
            }
        }
        scanner.close();
    }
}