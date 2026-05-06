package ua.edu.sumdu.j2se.pr9;

import java.util.UUID;

public abstract class Phone implements Identifiable {
    private UUID uuid;
    protected String brand;
    protected String model;
    protected int storage;
    protected double price;
    protected OsType os; 

    public Phone(String brand, String model, int storage, double price) {
        this.uuid = UUID.randomUUID(); 
        this.brand = brand;
        this.model = model;
        this.storage = storage;
        this.price = price;
    }

    public Phone(String brand, String model, int storage, double price, OsType os) {
        this.uuid = UUID.randomUUID(); 
        this.brand = brand;
        this.model = model;
        this.storage = storage;
        this.price = price;
        this.os = os;
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getStorage() { return storage; }
    public double getPrice() { return price; }
    public OsType getOs() { return os; }

    public void setPrice(double price) { this.price = price; }
    public void setStorage(int storage) { this.storage = storage; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setModel(String model) { this.model = model; }

    public String toFileString() {
        return toString(); 
    }

    @Override
    public String toString() {
        String osInfo = (os != null) ? " | OS: " + os : "";
        return "[UUID: " + uuid.toString() + "] Brand: " + brand + 
               " | Model: " + model + " | Storage: " + storage + 
               " GB | Price: $" + String.format("%.2f", price) + osInfo;
    }
}