package com.costume.service;

import com.costume.model.Category;
import com.costume.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int id) {
        return categoryRepository.getCategory(id);
    }

    public Category save(Category category) {
        if (category.getId() == null) {
            return categoryRepository.save(category);
        } else {
            Optional<Category> category1 = categoryRepository.getCategory(category.getId());
            if (category1.isEmpty()) {
                return categoryRepository.save(category);
            } else {
                return category;
            }
        }
    }

    public boolean deleteCategory(int id) {
        Optional<Category> unaCategoria = categoryRepository.getCategory(id);

        if (unaCategoria.isEmpty()) {
            return false;
        } else {
            categoryRepository.delete(unaCategoria.get());
            return true;
        }
    }
    
    /*
    public boolean deleteCategory(int id){
        Boolean d= getCategory(id).map(category -> {
            categoryRepository.delete(category);
            return true;
        }).orElse(false);
        return d;
    }
    */
    
    //{"id":1,"name":"modificada","description":"se ha modificado"}
    //{"id":1,"name":"modificada"}
    //{"name":"modificada"}
    public Category update(Category category) {
        if (category.getId() != null) {
            Optional<Category> g = categoryRepository.getCategory(category.getId());
            if (!g.isEmpty()) {
                if (category.getDescription() != null) {
                    g.get().setDescription(category.getDescription());
                }
                if (category.getName() != null) {
                    g.get().setName(category.getName());
                }
                return categoryRepository.save(g.get());
            }
        }
        return category;
    }

}
