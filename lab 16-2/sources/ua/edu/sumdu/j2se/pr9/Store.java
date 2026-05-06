package ua.edu.sumdu.j2se.pr9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class Store {
    private List<Phone> phones;

    public Store() {
        phones = new ArrayList<>();
    }

    public void addPhone(Phone phone) {
        phones.add(phone);
    }

    public void showAllPhones() {
        if (phones.isEmpty()) {
            System.out.println("The store is empty.");
            return;
        }
        for (Phone phone : phones) {
            System.out.println(phone);
        }
    }

    public void searchByBrand(String brand) {
        boolean found = false;
        for (Phone phone : phones) {
            if (phone.getBrand().equalsIgnoreCase(brand)) {
                System.out.println(phone);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No phones found with brand: " + brand);
        }
    }

    // UUID
    public void searchByUuid(String uuidStr) {
        try {
            UUID searchId = UUID.fromString(uuidStr);
            boolean found = false;
            
            for (Phone phone : phones) {
                if (phone.getUuid().equals(searchId)) {
                    System.out.println("\n-> Знайдено об'єкт:");
                    System.out.println(phone);
                    found = true;
                    break;
                }
            }
            
            if (!found) {
                System.out.println("-> Об'єкт з таким UUID не знайдено.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("-> Помилка: Некоректний формат UUID!");
        }
    }

    public void showSortedPhones(Comparator<Phone> comparator) {
        if (phones.isEmpty()) {
            System.out.println("Список порожній. Немає що сортувати.");
            return;
        }
        List<Phone> sortedPhones = new ArrayList<>(phones);
        sortedPhones.sort(comparator);
        
        System.out.println("\nВідсортована інформація про всі телефони");
        for (Phone phone : sortedPhones) {
            System.out.println(phone);
        }
        System.out.println("--------------------------------------------------");
    }

    public List<Phone> getPhones() {
        return phones;
    }
}