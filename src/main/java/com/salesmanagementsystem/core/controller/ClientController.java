package com.salesmanagementsystem.core.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable Long clientId) {
        Client client = clientService.getClientById(clientId);
        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody ClientRequestDTO clientRequestDTO) {
        String name = clientRequestDTO.getName();
        String lastName = clientRequestDTO.getLastName();
        String mobile = clientRequestDTO.getMobile();
        String email = clientRequestDTO.getEmail();
        String address = clientRequestDTO.getAddress();
        Client createdClient = clientService.createClient(name, lastName, mobile, email, address);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> updateClient(@PathVariable Long clientId, @RequestBody ClientRequestDTO clientRequestDTO) {
        String name = clientRequestDTO.getName();
        String lastName = clientRequestDTO.getLastName();
        String mobile = clientRequestDTO.getMobile();
        String email = clientRequestDTO.getEmail();
        String address = clientRequestDTO.getAddress();
        Client updatedClient = clientService.updateClient(clientId, name, lastName, mobile, email, address);
        logger.info("updated client is: {}", updatedClient);
        if (updatedClient != null) {
            return new ResponseEntity<>(updatedClient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long clientId) {
        clientService.deleteClient(clientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/report")
    public ResponseEntity<ClientReportDTO> getClientReport() {
        int totalNumberOfClients = clientService.getTotalNumberOfClients();
        List<Object[]> topSpendingClients = clientService.getTopSpendingClients();
        // Convert the data to DTO
        ClientReportDTO clientReportDTO = new ClientReportDTO(totalNumberOfClients, topSpendingClients);
        return new ResponseEntity<>(clientReportDTO, HttpStatus.OK);
    }
}
