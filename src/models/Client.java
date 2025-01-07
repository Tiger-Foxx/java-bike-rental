package models;

public class Client {
    private String id;        // Format : C100, C101, etc.
    private String name;
    private String phone;
    private String address;
    private int activeRentals; // Nombre de vélos actuellement loués

    public Client(String id, String name, String phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.activeRentals = 0; // Par défaut, aucune location
    }

    // Getters et setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public int getActiveRentals() {
        return activeRentals;
    }

    public void incrementRentals() {
        this.activeRentals++;
    }

    public void decrementRentals() {
        if (this.activeRentals > 0) {
            this.activeRentals--;
        }
    }
}
