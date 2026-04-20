package ua.edu.sumdu.j2se.pr8;

public class GamingPhone extends SmartPhone {
    private String coolingType;

    public GamingPhone(String brand, String model, int storage, double price, OsType os, int cam, String cooling) {
        super(brand, model, storage, price, os, cam);
        this.coolingType = cooling;
    }

    @Override
    public String toString() {
        return "Gaming" + super.toString() + " [Cooling: " + coolingType + "]";
    }
}