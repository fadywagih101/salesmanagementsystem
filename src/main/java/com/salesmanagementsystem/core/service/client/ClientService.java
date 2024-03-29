package com.salesmanagementsystem.core.service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanagementsystem.core.data.repository.ClientRepository;

@Service("clientService")
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public int getTotalNumberOfClients() {
        return clientRepository.getTotalNumberOfClients();
    }

    public List<Object[]> getTopSpendingClients() {
        return clientRepository.getTopSpendingClients();
    }
}
