package org.example.insuranceManagement.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.example.insuranceManagement.entity.Client;
import org.example.insuranceManagement.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository=clientRepository;
    }

    public List<Client> getClients(){
        return clientRepository.findAll();
    }

    public Client getInsuranceClient(Long id){
        Optional<Client> clientOptional=clientRepository.findById(id);

        if(!clientOptional.isPresent()){
            throw new IllegalStateException("Client with id "+id+" does not exist");
        }
        return clientOptional.get();
    }

    public Client addClient(Client client){
        Optional<Client> clientEmailOptional=clientRepository.findByEmail(client.getEmail());
        Optional<Client> clientPhoneOptional=clientRepository.findByPhone(client.getPhone());


        if(client.getName() == null || client.getName().isEmpty()){
            throw new IllegalStateException("Client name is required");
        }

        if(client.getEmail() == null || client.getEmail().isEmpty()){
            throw new IllegalStateException("Client email is required");
        }

        if(clientEmailOptional.isPresent()){
            throw new IllegalStateException("Client with this email already exists");
        }

    
        if(!client.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")){
            throw new IllegalStateException("Client email is not in correct format");
        }

        if(client.getPhone() == null || client.getPhone().isEmpty()){
            throw new IllegalStateException("Client phone is required");
        }

        // if(!client.getPhone().matches("^[0-9]{10}$")){
        //     throw new IllegalStateException("Client phone is not in correct format");
        // }

        if (clientPhoneOptional.isPresent()){
            throw new IllegalStateException("Client with this phone already exists");
        }

        Client addedClient= clientRepository.save(client);

        return addedClient;
    }

    public void deleteInsuranceClient(Long id){
        boolean exists = clientRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Client with id "+id+" does not exist");
        }
        clientRepository.deleteById(id);
    }

    @Transactional
    public Client updateInsuranceClient(Long id, Client client){
        Client clientToUpdate= clientRepository.findById(id).orElseThrow(()-> new IllegalStateException("Client with id "+id+" does not exist"));

        if(client.getName() != null && client.getName().length() > 0 && !Objects.equals(clientToUpdate.getName(), client.getName())){
            clientToUpdate.setName(client.getName());
        }

        if(client.getEmail() != null && client.getEmail().length() > 0 && !Objects.equals(clientToUpdate.getEmail(), client.getEmail())){
            Optional<Client> clientOptional=clientRepository.findByEmail(client.getEmail());
            if(clientOptional.isPresent()){
                throw new IllegalStateException("Client with this email already exists");
            }
            clientToUpdate.setEmail(client.getEmail());
        }

        if(client.getPhone() != null && client.getPhone().length() > 0 && !Objects.equals(clientToUpdate.getPhone(), client.getPhone())){
            Optional<Client> clientOptional=clientRepository.findByPhone(client.getPhone());
            if(clientOptional.isPresent()){
                throw new IllegalStateException("Client with this phone already exists");
            }
            clientToUpdate.setPhone(client.getPhone());
        }

        if(client.getNextOfKin() != null && client.getNextOfKin().length() > 0 && !Objects.equals(clientToUpdate.getNextOfKin(), client.getNextOfKin())){
            clientToUpdate.setNextOfKin(client.getNextOfKin());
        }

        if(client.getNextOfKinPhone() != null && client.getNextOfKinPhone().length() > 0 && !Objects.equals(clientToUpdate.getNextOfKinPhone(), client.getNextOfKinPhone())){
            clientToUpdate.setNextOfKinPhone(client.getNextOfKinPhone());
        }

        if(client.getNextOfKinEmail() != null && client.getNextOfKinEmail().length() > 0 && !Objects.equals(clientToUpdate.getNextOfKinEmail(), client.getNextOfKinEmail())){
            clientToUpdate.setNextOfKinEmail(client.getNextOfKinEmail());
        }

    
        if(client.getAddress() != null && client.getAddress().length() > 0 && !Objects.equals(clientToUpdate.getAddress(), client.getAddress())){
            clientToUpdate.setAddress(client.getAddress());
        }

        return clientToUpdate;
    }

}
