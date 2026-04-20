package ua.edu.sumdu.j2se.pr7;

public class KeypadPhone extends Phone {
    private boolean hasFlashlight;

    public KeypadPhone(String brand, String model, int storage, double price, boolean hasFlashlight) {
        super(brand, model, storage, price, OsType.PROPRIETARY);
        this.hasFlashlight = hasFlashlight;
    }

    @Override
    public String toString() {
        return "Keypad" + super.toString() + " [Flashlight: " + (hasFlashlight ? "Yes" : "No") + "]";
    }
}