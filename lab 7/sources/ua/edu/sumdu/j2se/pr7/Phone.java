package ua.edu.sumdu.j2se.pr7;

public class Phone {
    private String brand;
    private String model;
    private int storage;
    private double price;
    private OsType os;

    public Phone(String brand, String model, int storage, double price, OsType os) {
        setBrand(brand);
        setModel(model);
        setStorage(storage);
        setPrice(price);
        setOs(os);
    }

    public String getBrand() { return brand; }
    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) throw new IllegalArgumentException("Brand empty");
        this.brand = brand;
    }

    public String getModel() { return model; }
    public void setModel(String model) {
        if (model == null || model.trim().isEmpty()) throw new IllegalArgumentException("Model empty");
        this.model = model;
    }

    public int getStorage() { return storage; }
    public void setStorage(int storage) {
        if (storage <= 0) throw new IllegalArgumentException("Storage must be > 0");
        this.storage = storage;
    }

    public double getPrice() { return price; }
    public void setPrice(double price) {
        if (price <= 0) throw new IllegalArgumentException("Price must be > 0");
        this.price = price;
    }

    public OsType getOs() { return os; }
    public void setOs(OsType os) {
        this.os = (os == null) ? OsType.UNKNOWN : os;
    }

    @Override
    public String toString() {
        return String.format("Phone {Brand: '%s', Model: '%s', OS: %s, Storage: %dGB, Price: $%.2f}", 
                brand, model, os, storage, price);
    }
}