package ua.edu.sumdu.j2se.pr9;

public class Phone {
    private String brand;
    private String model;
    private int storage;
    private double price;
    private OsType os;

    public Phone(String brand, String model, int storage, double price, OsType os) {
        this.brand = brand;
        this.model = model;
        this.storage = storage;
        this.price = price;
        this.os = os;
    }

    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getStorage() { return storage; }
    public double getPrice() { return price; }
    public OsType getOs() { return os; }
    public String toFileString() {
        return String.format("PHONE|%s|%s|%d|%.2f|%s", brand, model, storage, price, os);
    }

    @Override
    public String toString() {
        return String.format("Phone {Brand: '%s', Model: '%s', OS: %s, Storage: %dGB, Price: $%.2f}", 
                brand, model, os, storage, price);
    }
}