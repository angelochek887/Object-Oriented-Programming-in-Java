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
            System.out.println("3. Show all objects");
            System.out.println("4. Вивести відсортовану інформацію про всі об'єкти");
            System.out.println("5. Exit");
            System.out.print("Your choice: ");

            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Wrong choice! Please enter a number.");
                scanner.nextLine(); // Очищення некоректного вводу
                continue;
            }

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
                    // Новий функціонал для 14 лаби
                    System.out.println("\nОберіть критерій сортування:");
                    System.out.println("1. За ціною (від найдешевшого до найдорожчого)");
                    System.out.println("2. За об'ємом пам'яті (від найбільшого до найменшого)");
                    System.out.println("3. За назвою бренду (за алфавітом)");
                    System.out.println("0. Повернутися в головне меню");
                    System.out.print("Ваш вибір: ");

                    int sortChoice = 0;
                    try {
                        sortChoice = scanner.nextInt();
                        scanner.nextLine(); // Очищення буфера
                    } catch (Exception e) {
                        System.out.println("Помилка: потрібно ввести цифру! Повернення до меню.");
                        scanner.nextLine(); // Очищення буфера
                        break; 
                    }

                    Comparator<Phone> comparator = null;

                    if (sortChoice == 1) {
                        // Анонімний клас: сортування за ціною (зростання)
                        comparator = new Comparator<Phone>() {
                            @Override
                            public int compare(Phone p1, Phone p2) {
                                return Double.compare(p1.getPrice(), p2.getPrice());
                            }
                        };
                    } else if (sortChoice == 2) {
                        // Анонімний клас: сортування за пам'яттю (спадання)
                        comparator = new Comparator<Phone>() {
                            @Override
                            public int compare(Phone p1, Phone p2) {
                                return Integer.compare(p2.getStorage(), p1.getStorage());
                            }
                        };
                    } else if (sortChoice == 3) {
                        // Анонімний клас: сортування за брендом (алфавіт)
                        comparator = new Comparator<Phone>() {
                            @Override
                            public int compare(Phone p1, Phone p2) {
                                return p1.getBrand().compareToIgnoreCase(p2.getBrand());
                            }
                        };
                    } else if (sortChoice == 0) {
                        System.out.println("Повернення до головного меню...");
                        break;
                    } else {
                        System.out.println("Невірний вибір критерію!");
                        break;
                    }

                    // Виклик методу сортування із переданим компаратором
                    store.showSortedPhones(comparator);
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