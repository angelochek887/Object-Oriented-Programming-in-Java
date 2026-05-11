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

    public void searchByUuid(String uuidStr) {
        try {
            UUID searchId = UUID.fromString(uuidStr);
            boolean found = false;
            for (Phone phone : phones) {
                if (phone.getUuid().equals(searchId)) {
                    System.out.println("\nObject found:");
                    System.out.println(phone);
                    found = true;
                    break;
                }
            }
            if (!found) System.out.println("Object not found.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid UUID format!");
        }
    }

    public void showSortedPhones(Comparator<Phone> comparator) {
        if (phones.isEmpty()) return;
        List<Phone> sortedPhones = new ArrayList<>(phones);
        sortedPhones.sort(comparator);
        for (Phone phone : sortedPhones) {
            System.out.println(phone);
        }
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public Phone getPhoneByUuid(String uuidStr) {
        try {
            UUID searchId = UUID.fromString(uuidStr);
            for (Phone phone : phones) {
                if (phone.getUuid().equals(searchId)) return phone;
            }
        } catch (Exception e) {}
        return null;
    }

    public void update(Phone existingObject, Phone newObject) throws ObjectNotFoundException {
        if (phones.isEmpty() || existingObject == null) {
            throw new ObjectNotFoundException("Collection is empty or object is missing.");
        }
        for (int i = 0; i < phones.size(); i++) {
            if (phones.get(i).getUuid().equals(existingObject.getUuid())) {
                phones.set(i, newObject);
                return;
            }
        }
        throw new ObjectNotFoundException("Object with UUID " + existingObject.getUuid() + " not found for update.");
    }

    public void delete(Phone existingObject) throws ObjectNotFoundException {
        if (phones.isEmpty() || existingObject == null) {
            throw new ObjectNotFoundException("Collection is empty or object is missing.");
        }
        for (int i = 0; i < phones.size(); i++) {
            if (phones.get(i).getUuid().equals(existingObject.getUuid())) {
                phones.remove(i);
                return;
            }
        }
        throw new ObjectNotFoundException("Object with UUID " + existingObject.getUuid() + " not found for deletion.");
    }
}