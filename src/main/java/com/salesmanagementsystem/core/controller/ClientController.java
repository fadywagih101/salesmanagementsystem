package com.salesmanagementsystem.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesmanagementsystem.core.model.client.Client;
import com.salesmanagementsystem.core.model.client.ClientReportDTO;
import com.salesmanagementsystem.core.model.client.ClientRequestDTO;
import com.salesmanagementsystem.core.service.client.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{clientId}")
    public Client getClientById(@PathVariable Long clientId) {
        return clientService.getClientById(clientId);
    }

    @PostMapping
    public Client createClient(@RequestBody ClientRequestDTO clientRequestDTO) {
        String name = clientRequestDTO.getName();
        String lastName = clientRequestDTO.getLastName();
        String mobile = clientRequestDTO.getMobile();
        String email = clientRequestDTO.getEmail();
        String address = clientRequestDTO.getAddress();
        return clientService.createClient(name, lastName, mobile, email, address);
    }

    @PutMapping("/{clientId}")
    public Client updateClient(@PathVariable Long clientId, @RequestBody ClientRequestDTO clientRequestDTO) {
        String name = clientRequestDTO.getName();
        String lastName = clientRequestDTO.getLastName();
        String mobile = clientRequestDTO.getMobile();
        String email = clientRequestDTO.getEmail();
        String address = clientRequestDTO.getAddress();
        return clientService.updateClient(clientId, name, lastName, mobile, email, address);
    }

    @DeleteMapping("/{clientId}")
    public void deleteClient(@PathVariable Long clientId) {
        clientService.deleteClient(clientId);
    }

    @GetMapping("/report")
    public ClientReportDTO getClientReport() {
        int totalNumberOfClients = clientService.getTotalNumberOfClients();
        List<Object[]> topSpendingClients = clientService.getTopSpendingClients();
        // Convert the data to DTO
        return new ClientReportDTO(totalNumberOfClients, topSpendingClients);
    }
}
