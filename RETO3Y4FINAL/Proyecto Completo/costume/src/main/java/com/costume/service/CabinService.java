package com.costume.service;

import com.costume.model.Cabin;
import com.costume.repository.CabinRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CabinService {

    @Autowired
    private CabinRepository cabinRepository;

    public List<Cabin> getAll() {
        return cabinRepository.getAll();
    }

    public Optional<Cabin> getCabin(int id) {
        return cabinRepository.getCabin(id);
    }

    public Cabin save(Cabin cabin) {
        if (cabin.getId() == null) {
            return cabinRepository.save(cabin);
        } else {
            Optional<Cabin> unDisfraz = cabinRepository.getCabin(cabin.getId());

            if (unDisfraz.isEmpty()) {
                return cabinRepository.save(cabin);
            } else {
                return cabin;
            }
        }
    }

    public boolean deleteCabin(int id) {
        Optional<Cabin> unDisfaz = cabinRepository.getCabin(id);

        if (unDisfaz.isEmpty()) {
            return false;
        } else {
            cabinRepository.delete(unDisfaz.get());
            return true;
        }
    }

    /*
        {"id":1,
        "brand":"DC Costumes",
        "name":"Superman",
        "description":"superman costume",
        "year":2021}
     */
    public Cabin update(Cabin cabin) {
        if (cabin.getId() != null) {
            Optional<Cabin> e = cabinRepository.getCabin(cabin.getId());
            if (!e.isEmpty()) {
                if (cabin.getName() != null) {
                    e.get().setName(cabin.getName());
                }
                if (cabin.getBrand() != null) {
                    e.get().setBrand(cabin.getBrand());
                }
                if (cabin.getRooms() != null) {
                    e.get().setRooms(cabin.getRooms());
                }
                if (cabin.getDescription() != null) {
                    e.get().setDescription(cabin.getDescription());
                }
                if (cabin.getCategory() != null) {
                    e.get().setCategory(cabin.getCategory());
                }
                cabinRepository.save(e.get());
                return e.get();
            } else {
                return cabin;
            }
        } else {
            return cabin;
        }
    }
}
