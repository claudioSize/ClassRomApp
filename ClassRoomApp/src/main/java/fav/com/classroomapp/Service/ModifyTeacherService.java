package fav.com.classroomapp.Service;


import fav.com.classroomapp.DAO.DTOModify;
import fav.com.classroomapp.Entitys.TeacherEntity;
import fav.com.classroomapp.Repository.TeacherRepository;
import fav.com.classroomapp.Repository.UserDetailsRepository;
import fav.com.classroomapp.Security.UserDtails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ModifyTeacherService {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    UserDetailsRepository userDetailsRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void modifyTeacher(Authentication authentication, DTOModify dtoTeacher){
        TeacherEntity teacherEntity = teacherRepository.findByInstitutionalId(authentication.getName())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        UserDtails userDtails = userDetailsRepository.findByInstitutionalId(authentication.getName())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

        userDtails.setPassword(passwordEncoder.encode(dtoTeacher.getPassword()));
        teacherEntity.setPassword(passwordEncoder.encode(dtoTeacher.getPassword()));
        teacherEntity.setName(dtoTeacher.getName());
        teacherEntity.setLasName(dtoTeacher.getLasName());

        teacherRepository.save(teacherEntity);
        userDetailsRepository.save(userDtails);


    }
}
