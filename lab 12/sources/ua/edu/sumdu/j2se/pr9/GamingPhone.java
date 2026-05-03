package ua.edu.sumdu.j2se.pr9;

public class GamingPhone extends SmartPhone {
    private String cooling;

    public GamingPhone(String brand, String model, int storage, double price, OsType os, int cameraMp, String cooling) {
        super(brand, model, storage, price, os, cameraMp);
        this.cooling = cooling;
    }

    public String getCooling() { return cooling; }

    @Override
    public String toFileString() {
        return "GAMINGPHONE|" + brand + "|" + model + "|" + storage + "|" + price + "|" + os + "|" + cameraMp + "|" + cooling;
    }

    @Override
    public String toString() {
        return String.format("GamingSmartphone {Brand: '%s', Model: '%s', OS: %s, Storage: %dGB, Price: $%.2f} [Camera: %dMP] [Cooling: %s]", 
                brand, model, os, storage, price, cameraMp, cooling);
    }
}