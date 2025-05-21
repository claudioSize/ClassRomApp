package fav.com.classroomapp.Service;

import fav.com.classroomapp.DAO.DTOModify;
import fav.com.classroomapp.Entitys.StudentsEntity;
import fav.com.classroomapp.Repository.StudentRepository;
import fav.com.classroomapp.Repository.UserDetailsRepository;
import fav.com.classroomapp.Security.UserDtails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
@Service
public class ModifyStudentService {



    @Autowired
    StudentRepository studentRepository;
    @Autowired
    UserDetailsRepository userDetailsRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void modify(Authentication authentication, DTOModify dtoTeacher){
        StudentsEntity students = studentRepository.findByInstitutionalId(authentication.getName())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        UserDtails userDtails = userDetailsRepository.findByInstitutionalId(authentication.getName())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

        userDtails.setPassword(passwordEncoder.encode(dtoTeacher.getPassword()));
        students.setPassword(passwordEncoder.encode(dtoTeacher.getPassword()));
        students.setName(dtoTeacher.getName());
        students.setLasName(dtoTeacher.getLasName());

        studentRepository.save(students);
        userDetailsRepository.save(userDtails);

    }
}
