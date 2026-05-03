package ua.edu.sumdu.j2se.pr9;

public class SatellitePhone extends Phone {
    private String provider;

    public SatellitePhone(String brand, String model, int storage, double price, String provider) {
        super(brand, model, storage, price, OsType.PROPRIETARY);
        this.provider = provider;
    }

    public String getProvider() { return provider; }

    @Override
    public String toFileString() {
        return "SATELLITEPHONE|" + brand + "|" + model + "|" + storage + "|" + price + "|" + os + "|" + provider;
    }

    @Override
    public String toString() {
        return String.format("SatellitePhone {Brand: '%s', Model: '%s', OS: %s, Storage: %dGB, Price: $%.2f} [Provider: %s]", 
                brand, model, os, storage, price, provider);
    }
}