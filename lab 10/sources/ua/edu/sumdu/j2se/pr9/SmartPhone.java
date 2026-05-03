package ua.edu.sumdu.j2se.pr9;

public class SmartPhone extends Phone {
    private int cameraMegapixels;

    public SmartPhone(String brand, String model, int storage, double price, OsType os, int cameraMegapixels) {
        super(brand, model, storage, price, os);
        this.cameraMegapixels = cameraMegapixels;
    }

    public int getCameraMegapixels() { return cameraMegapixels; }

    @Override
    public String toFileString() {
        return String.format("SMARTPHONE|%s|%s|%d|%.2f|%s|%d", 
                getBrand(), getModel(), getStorage(), getPrice(), getOs(), cameraMegapixels);
    }

    @Override
    public String toString() {
        return "Smart" + super.toString() + " [Camera: " + cameraMegapixels + "MP]";
    }
}