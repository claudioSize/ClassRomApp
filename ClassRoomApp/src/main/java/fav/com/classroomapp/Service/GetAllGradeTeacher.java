package fav.com.classroomapp.Service;

import fav.com.classroomapp.Entitys.GradesEntity;
import fav.com.classroomapp.Entitys.StudentsEntity;
import fav.com.classroomapp.Entitys.TeacherEntity;
import fav.com.classroomapp.Repository.GradesRepositoy;
import fav.com.classroomapp.Repository.StudentRepository;
import fav.com.classroomapp.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetAllGradeTeacher {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GradesRepositoy gradesRepositoy;

    public Map<String, List<Double>> getGrades(Authentication authentication){
        TeacherEntity teacherEntity = teacherRepository.findByInstitutionalId(authentication.getName())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Map<String,List<Double>> response = new HashMap<>();

        for (StudentsEntity studentsEntity : teacherEntity.getStudentsEntity()) {
            List<Double> getGradesR = new ArrayList<>();
            for (GradesEntity gradesEntity:studentsEntity.getGradesEntities()) {
                getGradesR.add(gradesEntity.getGrade());
            }
            response.put(studentsEntity.getName(),new ArrayList<>(getGradesR));
            getGradesR.clear();
        }
        if (response.isEmpty()) throw new ResponseStatusException(HttpStatus.CONFLICT,"Aun no existen notas");
        return response;
    }
}
