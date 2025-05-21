package fav.com.classroomapp.Service;


import fav.com.classroomapp.DAO.DTOStudent;
import fav.com.classroomapp.Entitys.StudentsEntity;
import fav.com.classroomapp.Entitys.SubjectsEntity;
import fav.com.classroomapp.Entitys.TeacherEntity;
import fav.com.classroomapp.Repository.StudentRepository;
import fav.com.classroomapp.Repository.TeacherRepository;
import fav.com.classroomapp.Repository.UserDetailsRepository;
import fav.com.classroomapp.Security.UserDtails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Service
public class CreateStudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    @Lazy
    PasswordEncoder passwordEncoder;
    @Autowired
    UserDetailsRepository userDetailsRepository;

    public void createStudents(DTOStudent student){
        if (!userDetailsRepository.existsByInstitutionalId(student.getInstitutionalId()) && !studentRepository.existsByInstitutionalId(student.getInstitutionalId())){
            studentRepository.save(dtoToEntity(student));
            userDetailsRepository.save(dtoToDetails(student));
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Identificacion institucional ya existe");
        }


    }
    public StudentsEntity dtoToEntity(DTOStudent student){
        Date date = new Date();
        TeacherEntity teacherEntity = teacherRepository.findByInstitutionalId(student.getTeacherInstitutionalId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Profesor no existe"));
        List<SubjectsEntity> subjectsEntities = new ArrayList<>();
        subjectsEntities.add(teacherEntity.getSubjectsEntity());

        StudentsEntity studentsEntity = new StudentsEntity();
        studentsEntity.setName(student.getName());
        studentsEntity.setLasName(student.getLasName());
        studentsEntity.setInstitutionalId(student.getInstitutionalId());
        studentsEntity.setPassword(passwordEncoder.encode(student.getPassword()));
        studentsEntity.setGuardianName(student.getGuardianName());
        studentsEntity.setGuardianNumber(student.getGuardianNumber());
        studentsEntity.setCreationDate(date);
        studentsEntity.setTeacherEntity(teacherEntity);
        studentsEntity.setSubjectsEntities(subjectsEntities);
        return studentsEntity;
    }
    public UserDtails dtoToDetails(DTOStudent dto){
        UserDtails userDtails = new UserDtails();
        userDtails.setInstitutionalId(dto.getInstitutionalId());
        userDtails.setPassword(passwordEncoder.encode(dto.getPassword()));
        userDtails.setRol("ROLE_STUDENT");
        userDtails.setEnabled(true);
        userDtails.setCredentialsNonExpired(true);
        userDtails.setAccountNonLocked(true);
        userDtails.setAccountNonExpired(true);
        return userDtails;
    }
}
