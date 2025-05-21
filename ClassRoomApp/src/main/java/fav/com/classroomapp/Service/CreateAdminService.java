package fav.com.classroomapp.Service;

import fav.com.classroomapp.DAO.DTOAdmin;
import fav.com.classroomapp.Entitys.AdminEntity;
import fav.com.classroomapp.Repository.AdminRepository;
import fav.com.classroomapp.Repository.UserDetailsRepository;
import fav.com.classroomapp.Security.UserDtails;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
public class CreateAdminService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    @Lazy

    PasswordEncoder passwordEncoder;
    @Autowired
    UserDetailsRepository userDetailsRepository;
    public void createA(DTOAdmin dto){
        if (!adminRepository.existsByInstitutionalId(dto.getInstitutionalId()) && !userDetailsRepository.existsByInstitutionalId(dto.getInstitutionalId())){
            userDetailsRepository.save(dtoToDetails(dto));
            adminRepository.save(dtoToEntity(dto));
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Identificacion institucional ya existe");
        }
    }
    public AdminEntity dtoToEntity(DTOAdmin dto){
        Date date = new Date();
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setName(dto.getName());
        adminEntity.setLasName(dto.getLastName());
        adminEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        adminEntity.setInstitutionalId(dto.getInstitutionalId());
        adminEntity.setCreationDate(date);
        return adminEntity;
    }
    public UserDtails dtoToDetails(DTOAdmin dto){
        UserDtails userDtails = new UserDtails();
        userDtails.setInstitutionalId(dto.getInstitutionalId());
        userDtails.setPassword(passwordEncoder.encode(dto.getPassword()));
        userDtails.setRol("ROLE_ADMIN");
        userDtails.setEnabled(true);
        userDtails.setCredentialsNonExpired(true);
        userDtails.setAccountNonLocked(true);
        userDtails.setAccountNonExpired(true);
        return userDtails;
    }
}
