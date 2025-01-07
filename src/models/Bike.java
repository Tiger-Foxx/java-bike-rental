package models;



public class Bike {
    private String id;       // Format : B200, B201, etc.
    private String brand;
    private String type;     // VTT, vélo de ville, etc.
    private String frameSize; // Taille du cadre
    private boolean isRented; // Statut de location

    public Bike(String id, String brand, String type, String frameSize) {
        this.id = id;
        this.brand = brand;
        this.type = type;
        this.frameSize = frameSize;
        this.isRented = false; // Par défaut, disponible
    }

    // Getters et setters
    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getType() {
        return type;
    }

    public String getFrameSize() {
        return frameSize;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        this.isRented = rented;
    }
}
