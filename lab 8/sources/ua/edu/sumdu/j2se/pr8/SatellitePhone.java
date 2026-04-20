package ua.edu.sumdu.j2se.pr8;

public class SatellitePhone extends Phone {
    private String satelliteProvider;

    public SatellitePhone(String brand, String model, int storage, double price, String provider) {
        super(brand, model, storage, price, OsType.PROPRIETARY);
        this.satelliteProvider = provider;
    }

    @Override
    public String toString() {
        return "Satellite" + super.toString() + " [Provider: " + satelliteProvider + "]";
    }
}