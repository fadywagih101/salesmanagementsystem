package com.salesmanagementsystem.core.service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanagementsystem.core.data.repository.ClientRepository;
import com.salesmanagementsystem.core.model.client.Client;

@Service("clientService")
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long clientId) {
        return clientRepository.findById(clientId).orElse(null);
    }

    public Client createClient(String name, String lastName, String mobile, String email, String address) {
        Client client = new Client(name, lastName, mobile, email, address);
        return clientRepository.save(client);
    }

    public Client updateClient(Long clientId, String name, String lastName, String mobile, String email, String address) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            client.setName(name);
            client.setLastName(lastName);
            client.setMobile(mobile);
            client.setEmail(email);
            client.setAddress(address);
            return clientRepository.save(client);
        }
        return null;
    }

    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }

    public int getTotalNumberOfClients() {
        return clientRepository.getTotalNumberOfClients();
    }

    public List<Object[]> getTopSpendingClients() {
        return clientRepository.getTopSpendingClients();
    }
}
