package com.costume.service;

import com.costume.model.Client;
import com.costume.repository.ClientRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    public Optional<Client> getClient(int id) {
        return clientRepository.getClient(id);
    }

    public Client save(Client client) {
        if (client.getIdClient() == null) {
            return clientRepository.save(client);
        } else {
            Optional<Client> unCliente = clientRepository.getClient(client.getIdClient());

            if (unCliente.isEmpty()) {
                return clientRepository.save(client);
            } else {
                return client;
            }
        }
    }

    public boolean deleteClient(int id) {
        Optional<Client> unCliente = clientRepository.getClient(id);

        if (unCliente.isEmpty()) {
            return false;
        } else {
            clientRepository.delete(unCliente.get());
            return true;
        }
    }
    
    /*
        {"idClient":1,
            "name":"Adeodato Sanchez",
            "email":"agustin@gmail.com",
            "password":"adeodato123",
            "age":15}
    */
    public Client update(Client client) {
        if (client.getIdClient() != null) {
            Optional<Client> e = clientRepository.getClient(client.getIdClient());
            if (!e.isEmpty()) {
                if (client.getName() != null) {
                    e.get().setName(client.getName());
                }
                if (client.getAge() != null) {
                    e.get().setAge(client.getAge());
                }
                if (client.getPassword() != null) {
                    e.get().setPassword(client.getPassword());
                }
                clientRepository.save(e.get());
                return e.get();
            } else {
                return client;
            }
        } else {
            return client;
        }
    }
}
