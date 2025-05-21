package fav.com.classroomapp.Service;

import fav.com.classroomapp.DAO.DTOCreateGrade;
import fav.com.classroomapp.Entitys.GradesEntity;
import fav.com.classroomapp.Entitys.StudentsEntity;
import fav.com.classroomapp.Entitys.TeacherEntity;
import fav.com.classroomapp.Repository.GradesRepositoy;
import fav.com.classroomapp.Repository.StudentRepository;
import fav.com.classroomapp.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CreateGradesService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    GradesRepositoy gradesRepositoy;

    public void createGrade(Authentication authentication, DTOCreateGrade dto){
        StudentsEntity students = studentRepository.findByInstitutionalId(dto.getInstitutionalId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"El estudiante no existe"));
        TeacherEntity teacherEntity = teacherRepository.findByInstitutionalId(authentication.getName())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"El profesor no existe"));

        gradesRepositoy.save(GradesEntity.builder()
                .grade(dto.getGrade()).studentsEntity(students).subjectsEntity(teacherEntity.getSubjectsEntity()).build());


    }
}
