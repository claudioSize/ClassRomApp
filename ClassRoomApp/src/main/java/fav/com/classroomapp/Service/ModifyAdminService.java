package fav.com.classroomapp.Service;

import fav.com.classroomapp.DAO.DTOModify;
import fav.com.classroomapp.Entitys.AdminEntity;
import fav.com.classroomapp.Entitys.StudentsEntity;
import fav.com.classroomapp.Repository.AdminRepository;
import fav.com.classroomapp.Repository.UserDetailsRepository;
import fav.com.classroomapp.Security.UserDtails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ModifyAdminService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    UserDetailsRepository userDetailsRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public void modifyDataMe(Authentication authentication, DTOModify dto){
        AdminEntity adminEntity = adminRepository.findAllByInstitutionalId(authentication.getName())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        UserDtails userDtails = userDetailsRepository.findByInstitutionalId(authentication.getName())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

        userDtails.setPassword(passwordEncoder.encode(dto.getPassword()));
        adminEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        adminEntity.setName(dto.getName());
        adminEntity.setLasName(dto.getLasName());

        adminRepository.save(adminEntity);
        userDetailsRepository.save(userDtails);

    }
}
