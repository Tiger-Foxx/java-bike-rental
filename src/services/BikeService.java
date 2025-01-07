package services;

import models.Bike;
import java.util.ArrayList;

public class BikeService {
    private ArrayList<Bike> bikes = new ArrayList<>();
    private int nextBikeId = 200; // Commence à B200

    public Bike addBike(String brand, String type, String frameSize) {
        if (bikes.size() >= 20) {
            System.out.println("Limite de vélos atteinte.");
            return null;
        }

        String bikeId = "B" + nextBikeId++;
        Bike bike = new Bike(bikeId, brand, type, frameSize);
        bikes.add(bike);
        return bike;
    }

    public ArrayList<Bike> getAvailableBikes() {
        ArrayList<Bike> availableBikes = new ArrayList<>();
        for (Bike bike : bikes) {
            if (!bike.isRented()) {
                availableBikes.add(bike);
            }
        }
        return availableBikes;
    }
}
