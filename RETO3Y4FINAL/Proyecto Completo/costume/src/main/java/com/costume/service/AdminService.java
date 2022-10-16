package com.costume.service;

import com.costume.model.Admin;
import com.costume.repository.AdminRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAll() {
        return adminRepository.getAll();
    }

    public Admin save(Admin admin) {
        if (admin.getIdAdmin() == null) {
            return adminRepository.save(admin);
        } else {
            Optional<Admin> admin1 = adminRepository.getAdmin(admin.getIdAdmin());
            if (admin1.isEmpty()) {
                return adminRepository.save(admin);
            } else {
                return admin;
            }
        }
    }

    public boolean deleteAdmin(int id) {
        Optional<Admin> admin = adminRepository.getAdmin(id);

        if (admin.isEmpty()) {
            return false;
        } else {
            adminRepository.delete(admin.get());
            return true;
        }
    }

    public Admin updateAdmin(Admin admin) {

        if (admin.getIdAdmin() != null) {
            Optional<Admin> unAdministrador = adminRepository.getAdmin(admin.getIdAdmin());
            
            if(!unAdministrador.isEmpty()){
                if (admin.getEmail()!=null){
                    unAdministrador.get().setEmail(admin.getEmail());
                }
                
                if (admin.getPassword()!=null){
                    unAdministrador.get().setPassword(admin.getPassword());
                }
                
                if (admin.getName()!=null){
                    unAdministrador.get().setName(admin.getName());
                }
            }

            return adminRepository.save(unAdministrador.get());
        }
        return admin;
    }
}
