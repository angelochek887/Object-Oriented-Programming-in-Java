package ua.edu.sumdu.j2se.pr9;

public class Phone {
    // Міняємо на protected, щоб підкласи бачили ці поля
    protected String brand;
    protected String model;
    protected int storage;
    protected double price;
    protected OsType os;

    public Phone(String brand, String model, int storage, double price, OsType os) {
        this.brand = brand;
        this.model = model;
        this.storage = storage;
        this.price = price;
        this.os = os;
    }

    // Гетери, які викликає DatabaseManager
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getStorage() { return storage; }
    public double getPrice() { return price; }
    public OsType getOs() { return os; }

    public String toFileString() {
        return "PHONE|" + brand + "|" + model + "|" + storage + "|" + price + "|" + os;
    }

    @Override
    public String toString() {
        return String.format("Phone {Brand: '%s', Model: '%s', OS: %s, Storage: %dGB, Price: $%.2f}", 
                brand, model, os, storage, price);
    }
}