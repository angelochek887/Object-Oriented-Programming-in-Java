package ua.edu.sumdu.j2se.pr7;

public class SmartPhone extends Phone {
    private int cameraMegapixels;

    public SmartPhone(String brand, String model, int storage, double price, OsType os, int cameraMegapixels) {
        super(brand, model, storage, price, os);
        setCameraMegapixels(cameraMegapixels);
    }

    public int getCameraMegapixels() { return cameraMegapixels; }
    public void setCameraMegapixels(int cameraMegapixels) {
        if (cameraMegapixels <= 0) throw new IllegalArgumentException("Camera MP must be > 0");
        this.cameraMegapixels = cameraMegapixels;
    }

    @Override
    public String toString() {
        return "Smart" + super.toString() + " [Camera: " + cameraMegapixels + "MP]";
    }
}