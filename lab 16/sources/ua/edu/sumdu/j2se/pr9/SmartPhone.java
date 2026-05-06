package ua.edu.sumdu.j2se.pr9;

public class SmartPhone extends Phone {
    protected int cameraMegapixels;

    public SmartPhone(String brand, String model, int storage, double price, OsType os, int cameraMegapixels) {
        super(brand, model, storage, price, os);
        this.cameraMegapixels = cameraMegapixels;
    }

    public SmartPhone(String brand, String model, int storage, double price, int cameraMegapixels) {
        super(brand, model, storage, price); 
        this.cameraMegapixels = cameraMegapixels;
    }

    public int getCameraMegapixels() {
        return cameraMegapixels;
    }

    @Override
    public String toFileString() {
        return "SmartPhone," + brand + "," + model + "," + storage + "," + price + "," + os + "," + cameraMegapixels;
    }

    @Override
    public String toString() {
        return "[SmartPhone] " + super.toString() + " | Camera: " + cameraMegapixels + " MP";
    }
}