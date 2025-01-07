package services;

import models.Client;
import java.util.ArrayList;

public class ClientService {
    private ArrayList<Client> clients = new ArrayList<>();
    private int nextClientId = 100; // Commence à C100

    public Client addClient(String name, String phone, String address) {
        if (clients.size() >= 10) {
            System.out.println("Limite de clients atteinte.");
            return null;
        }

        String clientId = "C" + nextClientId++;
        Client client = new Client(clientId, name, phone, address);
        clients.add(client);
        return client;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }
}
