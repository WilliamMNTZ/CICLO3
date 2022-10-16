package com.costume.repository.crud;

import com.costume.model.Cabin;
import org.springframework.data.repository.CrudRepository;

public interface CabinCrudRepository extends CrudRepository<Cabin, Integer> {
    
}
