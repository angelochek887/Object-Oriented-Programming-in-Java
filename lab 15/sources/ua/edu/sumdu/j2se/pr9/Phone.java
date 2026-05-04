package ua.edu.sumdu.j2se.pr9;

public abstract class Phone implements Comparable<Phone> {
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

    public Phone(String brand, String model, int storage, double price) {
        this.brand = brand;
        this.model = model;
        this.storage = storage;
        this.price = price;
        this.os = null; 
    }

    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getStorage() { return storage; }
    public double getPrice() { return price; }
    public OsType getOs() { return os; }

    public String toFileString() {
        return brand + "," + model + "," + storage + "," + price + "," + os;
    }

    @Override
    public int compareTo(Phone other) {
        int brandComparison = this.brand.compareToIgnoreCase(other.brand);
        if (brandComparison == 0) {
            return this.model.compareToIgnoreCase(other.model);
        }
        return brandComparison;
    }

    @Override
    public String toString() {
        return String.format("Brand: %s | Model: %s | Storage: %d GB | Price: $%.2f | OS: %s", 
                              brand, model, storage, price, os);
    }
}