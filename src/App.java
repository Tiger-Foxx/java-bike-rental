import java.util.ArrayList;
import models.*;
import services.*;
import utils.*;

public class App {
    private static ClientService clientService = new ClientService();
    private static BikeService bikeService = new BikeService();
    private static RentalService rentalService = new RentalService();

    public static void main(String[] args) {
        initializeData();
        while (true) {
            afficherMenu();
            int choix = InputUtils.readInt("Votre choix : ", 1, 6);
            
            switch (choix) {
                case 1: ajouterClient(); break;
                case 2: enregistrerVelo(); break;
                case 3: louerVelo(); break;
                case 4: retournerVelo(); break;
                case 5: afficherLocations(); break;
                case 6: 
                    System.out.println("Au revoir !");
                    return;
            }
        }
    }

    private static void afficherMenu() {
        System.out.println("\n=== BikeRent - Système de Location ===");
        System.out.println("1. Ajouter un client");
        System.out.println("2. Enregistrer un vélo");
        System.out.println("3. Louer un vélo");
        System.out.println("4. Retourner un vélo");
        System.out.println("5. Afficher les locations en cours");
        System.out.println("6. Quitter");
    }

    private static void ajouterClient() {
        System.out.println("\n=== Ajout d'un nouveau client ===");
        String nom = InputUtils.readString("Nom : ");
        String telephone = InputUtils.readString("Téléphone : ");
        String adresse = InputUtils.readString("Adresse : ");

        Client client = clientService.addClient(nom, telephone, adresse);
        if (client != null) {
            System.out.println("Client ajouté avec succès ! ID: " + client.getId());
        }
    }

    private static void enregistrerVelo() {
        System.out.println("\n=== Enregistrement d'un nouveau vélo ===");
        String marque = InputUtils.readString("Marque : ");
        String type = InputUtils.readString("Type (VTT, vélo de ville, etc.) : ");
        String tailleCadre = InputUtils.readString("Taille du cadre : ");

        Bike velo = bikeService.addBike(marque, type, tailleCadre);
        if (velo != null) {
            System.out.println("Vélo ajouté avec succès ! ID: " + velo.getId());
        }
    }

    private static void louerVelo() {
        System.out.println("\n=== Location d'un vélo ===");
        
        /* Afficher la liste des clients icii*/
        ArrayList<Client> clients = clientService.getClients();
        if (clients.isEmpty()) {
            System.out.println("Aucun client enregistré.");
            return;
        }

        System.out.println("Clients disponibles :");
        for (Client client : clients) {
            System.out.printf("%s - %s (%d locations actives)%n", 
                client.getId(), client.getName(), client.getActiveRentals());
        }

        String clientId = InputUtils.readString("ID du client : ");
        Client selectedClient = null;
        for (Client client : clients) {
            if (client.getId().equals(clientId)) {
                selectedClient = client;
                break;
            }
        }

        if (selectedClient == null) {
            System.out.println("Client non trouvé.");
            return;
        }

        /** les velos dispo */
        ArrayList<Bike> velosDisponibles = bikeService.getAvailableBikes();
        if (velosDisponibles.isEmpty()) {
            System.out.println("Aucun vélo disponible.");
            return;
        }

        System.out.println("Vélos disponibles :");
        for (Bike velo : velosDisponibles) {
            System.out.printf("%s - %s %s (%s)%n", 
                velo.getId(), velo.getBrand(), velo.getType(), velo.getFrameSize());
        }

        String veloId = InputUtils.readString("ID du vélo : ");
        Bike selectedBike = null;
        for (Bike velo : velosDisponibles) {
            if (velo.getId().equals(veloId)) {
                selectedBike = velo;
                break;
            }
        }

        if (selectedBike == null) {
            System.out.println("Vélo non trouvé ou non disponible.");
            return;
        }

        if (rentalService.rentBike(selectedClient, selectedBike)) {
            System.out.println("Location effectuée avec succès !");
        }
    }

    private static void retournerVelo() {
        System.out.println("\n=== Retour d'un vélo ===");
        
        String clientId = InputUtils.readString("ID du client : ");
        Client selectedClient = null;
        for (Client client : clientService.getClients()) {
            if (client.getId().equals(clientId)) {
                selectedClient = client;
                break;
            }
        }

        if (selectedClient == null) {
            System.out.println("Client non trouvé.");
            return;
        }

        ArrayList<Rental> clientRentals = rentalService.getClientRentals(selectedClient);
        if (clientRentals.isEmpty()) {
            System.out.println("Ce client n'a aucune location en cours.");
            return;
        }

        System.out.println("Locations en cours pour " + selectedClient.getName() + " :");
        for (Rental rental : clientRentals) {
            Bike bike = rental.getBike();
            System.out.printf("%s - %s %s (loué le %s)%n",
                bike.getId(), bike.getBrand(), bike.getType(),
                DateUtils.formatDate(rental.getRentalDate()));
        }

        String bikeId = InputUtils.readString("ID du vélo à retourner : ");
        Bike selectedBike = null;
        for (Rental rental : clientRentals) {
            if (rental.getBike().getId().equals(bikeId)) {
                selectedBike = rental.getBike();
                break;
            }
        }

        if (selectedBike == null) {
            System.out.println("Vélo non trouvé dans les locations de ce client.");
            return;
        }

        if (rentalService.returnBike(selectedClient, selectedBike)) {
            System.out.println("Retour effectué avec succès !");
        }
    }

    private static void afficherLocations() {
        System.out.println("\n=== Locations en cours ===");
        ArrayList<Rental> locations = rentalService.getActiveRentals();
        
        if (locations.isEmpty()) {
            System.out.println("Aucune location en cours.");
            return;
        }

        for (Rental location : locations) {
            Bike velo = location.getBike();
            Client client = location.getClient();
            System.out.printf("""
                Numéro du vélo : %s
                Vélo : %s %s, taille du cadre : %s
                Client : %s (%s)
                Date de location : %s
                %n""",
                velo.getId(),
                velo.getBrand(), velo.getType(), velo.getFrameSize(),
                client.getName(), client.getId(),
                DateUtils.formatDate(location.getRentalDate()));
        }
    }

    private static void initializeData() {
        Client c1 = clientService.addClient("Jean Dupont", "0123456789", "1 rue de Paris");
        Client c2 = clientService.addClient("Marie Martin", "0234567890", "2 avenue des Champs");
        Client c3 = clientService.addClient("Pierre Durant", "0345678901", "3 boulevard du Mail");
        Client c4 = clientService.addClient("Sophie Lefebvre", "0456789012", "4 place de la République");

        Bike b1 = bikeService.addBike("Trek", "VTT", "M");
        Bike b2 = bikeService.addBike("Specialized", "Route", "L");
        Bike b3 = bikeService.addBike("Cannondale", "Ville", "S");
        Bike b4 = bikeService.addBike("Giant", "VTT", "XL");
        Bike b5 = bikeService.addBike("Scott", "Route", "M");

        rentalService.rentBike(c1, b1);
        rentalService.rentBike(c2, b2);
        rentalService.rentBike(c3, b3);
        rentalService.rentBike(c1, b4);
        rentalService.rentBike(c2, b5);
    }
}