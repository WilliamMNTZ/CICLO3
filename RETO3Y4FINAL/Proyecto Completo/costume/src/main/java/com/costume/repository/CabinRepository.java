package com.costume.repository;

import com.costume.model.Cabin;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.costume.repository.crud.CabinCrudRepository;

@Repository
public class CabinRepository {
    @Autowired
    private CabinCrudRepository cabinCrudRepository;
    
    public List<Cabin> getAll(){
        return (List<Cabin>) cabinCrudRepository.findAll();
    }
    
    public Optional<Cabin> getCabin(int id){
        return cabinCrudRepository.findById(id);
    }
    
    public Cabin save(Cabin cabin){
        return cabinCrudRepository.save(cabin);
    }
    
     public void delete(Cabin cabin) {
        cabinCrudRepository.delete(cabin);
    } 
}
