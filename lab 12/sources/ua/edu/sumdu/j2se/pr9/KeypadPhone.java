package ua.edu.sumdu.j2se.pr9;

public class KeypadPhone extends Phone {
    private boolean hasFlashlight;

    public KeypadPhone(String brand, String model, int storage, double price, boolean hasFlashlight) {
        super(brand, model, storage, price, OsType.PROPRIETARY);
        this.hasFlashlight = hasFlashlight;
    }

    public boolean isHasFlashlight() { return hasFlashlight; }

    @Override
    public String toFileString() {
        return "KEYPADPHONE|" + brand + "|" + model + "|" + storage + "|" + price + "|" + os + "|" + hasFlashlight;
    }

    @Override
    public String toString() {
        return String.format("KeypadPhone {Brand: '%s', Model: '%s', OS: %s, Storage: %dGB, Price: $%.2f} [Flashlight: %s]", 
                brand, model, os, storage, price, (hasFlashlight ? "Yes" : "No"));
    }
}