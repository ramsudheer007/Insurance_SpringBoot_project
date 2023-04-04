package org.example.insuranceManagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.insuranceManagement.entity.Client;
import org.example.insuranceManagement.services.ClientService;
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


@RestController
@RequestMapping(path="api/v1/clients")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService=clientService;
    }

    @GetMapping
    public List<Client> getClients(){
        return clientService.getClients();
    }

    @GetMapping(path="/{clientId}")
    public Client getInsuranceClient(@PathVariable("clientId") Long id){
        return clientService.getInsuranceClient(id);
    }


    @PostMapping("/")
    public ResponseEntity<Client> addClient(@RequestBody Client client){
        System.out.println("Client: "+client);
        Client newClient= clientService.addClient(client);

        return ResponseEntity.ok(newClient);
    }

    @DeleteMapping(path="/{clientId}")
    public ResponseEntity<Object> deleteInsuranceClient(@PathVariable("clientId") Long id){
        clientService.deleteInsuranceClient(id);
         Map<String, String> message = new HashMap<>();
         message.put("message", "Product was successfully deleted");

        return new ResponseEntity<>(message, HttpStatus.OK);

    }

    @PutMapping("/{clientId}/")
    public ResponseEntity<Client> updateInsuranceClient(
        @PathVariable("clientId") Long id,
        @RequestBody Client client
    ){
        Client updatedClient=clientService.updateInsuranceClient(id, client);

        return ResponseEntity.ok(updatedClient);
    }

    
}
