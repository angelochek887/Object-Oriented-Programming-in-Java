package ua.edu.sumdu.j2se.pr9;

public class GamingPhone extends SmartPhone {
    private String coolingType;

    public GamingPhone(String brand, String model, int storage, double price, OsType os, int cam, String cooling) {
        super(brand, model, storage, price, os, cam);
        this.coolingType = cooling;
    }

    @Override
    public String toFileString() {
        return String.format("GAMINGPHONE|%s|%s|%d|%.2f|%s|%d|%s", 
                getBrand(), getModel(), getStorage(), getPrice(), getOs(), getCameraMegapixels(), coolingType);
    }

    @Override
    public String toString() {
        return "Gaming" + super.toString() + " [Cooling: " + coolingType + "]";
    }
}