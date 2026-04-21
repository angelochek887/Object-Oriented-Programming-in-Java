package ua.edu.sumdu.j2se.pr9;

public class SatellitePhone extends Phone {
    private String satelliteProvider;

    public SatellitePhone(String brand, String model, int storage, double price, String provider) {
        super(brand, model, storage, price, OsType.PROPRIETARY);
        this.satelliteProvider = provider;
    }

    @Override
    public String toFileString() {
        return String.format("SATELLITEPHONE|%s|%s|%d|%.2f|%s|%s", 
                getBrand(), getModel(), getStorage(), getPrice(), getOs(), satelliteProvider);
    }

    @Override
    public String toString() {
        return "Satellite" + super.toString() + " [Provider: " + satelliteProvider + "]";
    }
}