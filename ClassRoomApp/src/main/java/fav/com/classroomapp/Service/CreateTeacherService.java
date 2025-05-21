package fav.com.classroomapp.Service;


import fav.com.classroomapp.DAO.DTOTeacher;
import fav.com.classroomapp.Entitys.SubjectsEntity;
import fav.com.classroomapp.Entitys.TeacherEntity;
import fav.com.classroomapp.Repository.SubjectRepository;
import fav.com.classroomapp.Repository.TeacherRepository;
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
public class CreateTeacherService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    @Lazy
    TeacherRepository teacherRepository;
    @Autowired
    UserDetailsRepository userDetailsRepository;
    @Autowired
    SubjectRepository subjectRepository;

    public void createTe(DTOTeacher dto){
        if (!userDetailsRepository.existsByInstitutionalId(dto.getInstitutionalId()) && !teacherRepository.existsByInstitutionalId(dto.getInstitutionalId())){
            userDetailsRepository.save(dtoToDetails(dto));
            teacherRepository.save(dtoToEntity(dto));

        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Identificacion institucional ya existe");
        }

    }
    public TeacherEntity dtoToEntity(DTOTeacher dto){
        SubjectsEntity subjectsEntity = subjectRepository.findBySubjectName(dto.getSubject())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"La materia no existe"));
        Date date = new Date();
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setSubjectsEntity(subjectsEntity);
        teacherEntity.setStudentCount(dto.getStudentCount());
        teacherEntity.setName(dto.getName());
        teacherEntity.setLasName(dto.getLasName());
        teacherEntity.setInstitutionalId(dto.getInstitutionalId());
        teacherEntity.setCreationDate(date);
        teacherEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        return teacherEntity;
    }
    public UserDtails dtoToDetails(DTOTeacher dto){
        UserDtails userDtails = new UserDtails();
        userDtails.setInstitutionalId(dto.getInstitutionalId());
        userDtails.setPassword(passwordEncoder.encode(dto.getPassword()));
        userDtails.setRol("ROLE_TEACHER");
        userDtails.setEnabled(true);
        userDtails.setCredentialsNonExpired(true);
        userDtails.setAccountNonLocked(true);
        userDtails.setAccountNonExpired(true);
        return userDtails;
    }
}
