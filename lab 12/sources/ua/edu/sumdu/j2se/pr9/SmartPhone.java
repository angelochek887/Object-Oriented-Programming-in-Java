package ua.edu.sumdu.j2se.pr9;

public class SmartPhone extends Phone {
    protected int cameraMp;

    public SmartPhone(String brand, String model, int storage, double price, OsType os, int cameraMp) {
        super(brand, model, storage, price, os);
        this.cameraMp = cameraMp;
    }

    public int getCameraMp() { return cameraMp; }

    @Override
    public String toFileString() {
        return "SMARTPHONE|" + brand + "|" + model + "|" + storage + "|" + price + "|" + os + "|" + cameraMp;
    }

    @Override
    public String toString() {
        return String.format("SmartPhone {Brand: '%s', Model: '%s', OS: %s, Storage: %dGB, Price: $%.2f} [Camera: %dMP]", 
                brand, model, os, storage, price, cameraMp);
    }
}