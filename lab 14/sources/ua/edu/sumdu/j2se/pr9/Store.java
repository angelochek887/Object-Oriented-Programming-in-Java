package ua.edu.sumdu.j2se.pr9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Store {
    private List<Phone> phones;

    public Store() {
        this.phones = new ArrayList<>();
    }

    public void addPhone(Phone phone) {
        phones.add(phone);
    }

    public void showAllPhones() {
        if (phones.isEmpty()) {
            System.out.println("Список порожній.");
            return;
        }
        for (Phone p : phones) {
            System.out.println(p.toString());
        }
    }

    public void searchByBrand(String brand) {
        boolean found = false;
        for (Phone p : phones) {
            if (p.getBrand().equalsIgnoreCase(brand)) {
                System.out.println(p.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Об'єктів із таким брендом не знайдено.");
        }
    }

    // Оновлений метод для 14 лаби: тепер приймає Comparator
    public void showSortedPhones(Comparator<Phone> comparator) {
        if (phones.isEmpty()) {
            System.out.println("Список порожній! Немає що сортувати.");
            return;
        }

        List<Phone> sortedList = new ArrayList<>(phones);

        // Передаємо наш критерій сортування у метод Collections.sort
        Collections.sort(sortedList, comparator);
        
        System.out.println("\nВідсортована інформація про всі телефони");
        for (Phone p : sortedList) {
            System.out.println(p.toString());
        }
        System.out.println("------------------------------------------------");
    }
}