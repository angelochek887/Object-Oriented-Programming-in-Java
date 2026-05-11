package ua.edu.sumdu.j2se.pr9;

import java.util.Scanner;
import java.util.Comparator;

public class PhoneDriver {
    public static void main(String[] args) {
        Store store = new Store();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nMain Menu");
            System.out.println("1. Search object");
            System.out.println("2. Create new object");
            System.out.println("3. Modify object");
            System.out.println("4. Delete object");
            System.out.println("5. Show all objects");
            System.out.println("6. Show sorted objects");
            System.out.println("7. Exit");
            System.out.print("Your choice: ");

            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Wrong choice! Please enter a number.");
                scanner.nextLine(); 
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("\nChoose search criteria:");
                    System.out.println("1. Search by brand");
                    System.out.println("2. Search by UUID");
                    System.out.print("Your choice: ");
                    
                    int searchChoice = 0;
                    try {
                        searchChoice = scanner.nextInt();
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("Input error!");
                        scanner.nextLine();
                        break;
                    }

                    if (searchChoice == 1) {
                        System.out.print("Enter brand to search: ");
                        String searchBrand = scanner.nextLine();
                        store.searchByBrand(searchBrand);
                    } else if (searchChoice == 2) {
                        System.out.print("Enter UUID: ");
                        String searchUuid = scanner.nextLine();
                        store.searchByUuid(searchUuid);
                    } else {
                        System.out.println("Invalid choice.");
                    }
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

                    try {
                        System.out.print("Enter Brand: ");
                        String brand = scanner.nextLine();
                        System.out.print("Enter Model: ");
                        String model = scanner.nextLine();
                        System.out.print("Enter Storage (GB): ");
                        int storage = scanner.nextInt();
                        System.out.print("Enter Price ($): ");
                        double price = scanner.nextDouble();
                        scanner.nextLine();

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
                    } catch (InvalidFieldValueException e) {
                        System.out.println("-> Creation error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("-> Invalid data input!");
                        scanner.nextLine();
                    }
                    break;
                case 3:
                    if (store.getPhones().isEmpty()) {
                        System.out.println("-> Collection is empty! Nothing to modify.");
                        break;
                    }
                    System.out.print("\nEnter object UUID for modification: ");
                    String modUuid = scanner.nextLine();
                    Phone phoneToMod = store.getPhoneByUuid(modUuid);
                    
                    if (phoneToMod == null) {
                        System.out.println("-> Error: Object not found!");
                    } else {
                        System.out.println("Found: " + phoneToMod);
                        System.out.println("Which attribute do you want to change?");
                        System.out.println("1. Price");
                        System.out.println("2. Storage");
                        System.out.print("Your choice: ");
                        
                        int modChoice = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (modChoice == 1) {
                            System.out.print("Enter new price: ");
                            double newPrice = scanner.nextDouble();
                            scanner.nextLine();
                            try {
                                phoneToMod.setPrice(newPrice);
                                store.update(phoneToMod, phoneToMod);
                                System.out.println("-> Price updated successfully!");
                            } catch (InvalidFieldValueException | ObjectNotFoundException e) {
                                System.out.println("-> Update rejected: " + e.getMessage());
                            }

                        } else if (modChoice == 2) {
                            System.out.print("Enter new storage capacity: ");
                            int newStorage = scanner.nextInt();
                            scanner.nextLine();
                            try {
                                phoneToMod.setStorage(newStorage);
                                store.update(phoneToMod, phoneToMod);
                                System.out.println("-> Storage updated successfully!");
                            } catch (InvalidFieldValueException | ObjectNotFoundException e) {
                                System.out.println("-> Update rejected: " + e.getMessage());
                            }
                        } else {
                            System.out.println("-> Invalid attribute choice.");
                        }
                    }
                    break;
                case 4:
                    if (store.getPhones().isEmpty()) {
                        System.out.println("-> Collection is empty! Nothing to delete.");
                        break;
                    }
                    System.out.print("\nEnter object UUID for deletion: ");
                    String delUuid = scanner.nextLine();
                    Phone phoneToDel = store.getPhoneByUuid(delUuid);
                    
                    if (phoneToDel == null) {
                        System.out.println("-> Error: Object not found!");
                    } else {
                        System.out.println("Found: " + phoneToDel);
                        System.out.print("Are you sure you want to delete this object? (y/n): ");
                        String confirm = scanner.nextLine();
                        
                        if (confirm.equalsIgnoreCase("y")) {
                            try {
                                store.delete(phoneToDel);
                                System.out.println("-> Object deleted successfully!");
                            } catch (ObjectNotFoundException e) {
                                System.out.println("-> Deletion error: " + e.getMessage());
                            }
                        } else {
                            System.out.println("-> Deletion cancelled.");
                        }
                    }
                    break;
                case 5:
                    store.showAllPhones();
                    break;
                case 6:
                    System.out.println("\nChoose sorting criteria:");
                    System.out.println("1. By price (ascending)");
                    System.out.println("2. By storage (descending)");
                    System.out.println("3. By brand name (alphabetical)");
                    System.out.println("0. Back to main menu");
                    System.out.print("Your choice: ");

                    int sortChoice = 0;
                    try {
                        sortChoice = scanner.nextInt();
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("Error: a number is required! Returning to menu.");
                        scanner.nextLine();
                        break; 
                    }

                    Comparator<Phone> comparator = null;

                    if (sortChoice == 1) {
                        comparator = (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice());
                    } else if (sortChoice == 2) {
                        comparator = (p1, p2) -> Integer.compare(p2.getStorage(), p1.getStorage());
                    } else if (sortChoice == 3) {
                        comparator = (p1, p2) -> p1.getBrand().compareToIgnoreCase(p2.getBrand());
                    } else if (sortChoice == 0) {
                        System.out.println("Returning to main menu...");
                        break;
                    } else {
                        System.out.println("Invalid criteria choice!");
                        break;
                    }

                    store.showSortedPhones(comparator);
                    break;
                case 7:
                    System.out.println("Exiting program...");
                    running = false;
                    break;
                default:
                    System.out.println("Wrong choice! Please enter a number from 1 to 7.");
            }
        }
        scanner.close();
    }
}