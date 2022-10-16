package com.costume.repository.crud;

import com.costume.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientCrudRepository extends CrudRepository<Client, Integer>{
    
}
