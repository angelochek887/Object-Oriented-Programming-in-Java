package ua.edu.sumdu.j2se.pr9;

import java.util.ArrayList;

public class Store {
    private String storeName;
    private ArrayList<Phone> phones;
    private ArrayList<Integer> quantities;

    public Store(String storeName) {
        this.storeName = storeName;
        this.phones = new ArrayList<>();
        this.quantities = new ArrayList<>();
    }

    public void addNewPhone(Phone ph, int quantity) {
        for (int i = 0; i < phones.size(); i++) {
            Phone existingPhone = phones.get(i);
            if (existingPhone.getBrand().equalsIgnoreCase(ph.getBrand()) && 
                existingPhone.getModel().equalsIgnoreCase(ph.getModel())) {
                
                int currentQty = quantities.get(i);
                quantities.set(i, currentQty + quantity);
                System.out.println("-> Item already exists. Increased quantity. Total: " + quantities.get(i));
                return;
            }
        }

        phones.add(ph);
        quantities.add(quantity);
        System.out.println("-> New item added to store with quantity: " + quantity);
    }

    public void showAll() {
        if (phones.isEmpty()) {
            System.out.println("Store is empty.");
            return;
        }
        System.out.println("\n" + storeName + " Inventory ");
        for (int i = 0; i < phones.size(); i++) {
            System.out.println(phones.get(i).toString() + " | In stock: " + quantities.get(i) + " pcs");
        }
    }

    public void searchByBrand(String targetBrand) {
        boolean found = false;
        System.out.println("\nSearch Results (Brand: " + targetBrand + ") ");
        for (int i = 0; i < phones.size(); i++) {
            if (phones.get(i).getBrand().equalsIgnoreCase(targetBrand)) {
                System.out.println(phones.get(i).toString() + " | In stock: " + quantities.get(i) + " pcs");
                found = true;
            }
        }
        if (!found) System.out.println("No devices found matching this brand.");
    }

    public void searchByMaxPrice(double maxPrice) {
        boolean found = false;
        System.out.println("\nSearch Results (Price <= $" + maxPrice + ") ");
        for (int i = 0; i < phones.size(); i++) {
            if (phones.get(i).getPrice() <= maxPrice) {
                System.out.println(phones.get(i).toString() + " | In stock: " + quantities.get(i) + " pcs");
                found = true;
            }
        }
        if (!found) System.out.println("No devices found in this price range.");
    }

    public void searchByMinStorage(int minStorage) {
        boolean found = false;
        System.out.println("\nSearch Results (Storage >= " + minStorage + "GB) ");
        for (int i = 0; i < phones.size(); i++) {
            if (phones.get(i).getStorage() >= minStorage) {
                System.out.println(phones.get(i).toString() + " | In stock: " + quantities.get(i) + " pcs");
                found = true;
            }
        }
        if (!found) System.out.println("No devices found with such storage capacity.");
    }

    public ArrayList<Phone> getPhones() { return phones; }
    public ArrayList<Integer> getQuantities() { return quantities; }
}