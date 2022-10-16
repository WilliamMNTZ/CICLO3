/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Servicio;

import com.example.demo.Repositorio.CabinRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Modelo.Cabin;
/**
 *
 * @author USUARIO
 */

@Service

public class CabinService {
      @Autowired
    private CabinRepository cabinRepository;
    
    public List<Cabin> getAll(){
        return cabinRepository.getAll();
    }
    
    public Optional<Cabin> getCabin(int id){
        return cabinRepository.getCabin(id);
    }
    
    public Cabin save (Cabin cabin){
        if (cabin.getId() == null){
            return cabinRepository.save(cabin);
        } else {
            Optional<Cabin> cabinn = cabinRepository.getCabin(cabin.getId());
            if(cabinn.isEmpty()){
                return cabinRepository.save(cabin);
            } else {
                return cabin;
            }
        }
    }
    
       public Cabin update(Cabin cabin){
        if(cabin.getId()!=null){
            Optional<Cabin> e= cabinRepository.getCabin(cabin.getId());
            if(!e.isEmpty()){
                if(cabin.getName()!=null){
                    e.get().setName(cabin.getName());
                }
                if(cabin.getBrand()!=null){
                    e.get().setBrand(cabin.getBrand());
                }
                if(cabin.getRooms()!=null){
                    e.get().setRooms(cabin.getRooms());
                }
                if(cabin.getDescription()!=null){
                    e.get().setDescription(cabin.getDescription());
                }
                if(cabin.getCategory()!=null){
                    e.get().setCategory(cabin.getCategory());
                }
                cabinRepository.save(e.get());
                return e.get();
            }else{
                return cabin;
            }
        }else{
            return cabin;
        }
    }
    
    
         public boolean deleteCabin(int id){
        Boolean d = getCabin(id).map(cabin -> {
            cabinRepository.delete(cabin);
            return true;
        }).orElse(false);
        return d;  
    }
}
