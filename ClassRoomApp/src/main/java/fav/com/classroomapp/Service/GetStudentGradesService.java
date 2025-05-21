package fav.com.classroomapp.Service;


import fav.com.classroomapp.DAO.DTOGradesGetStudent;
import fav.com.classroomapp.Entitys.GradesEntity;
import fav.com.classroomapp.Entitys.StudentsEntity;
import fav.com.classroomapp.Entitys.SubjectsEntity;
import fav.com.classroomapp.Repository.GradesRepositoy;
import fav.com.classroomapp.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetStudentGradesService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GradesRepositoy gradesRepositoy;

    //Busca las notas en la db por el userName de userdetails almacenado en el contexto en este caso se busca por la id institucional
    //luego itero la fk de la clase student y vuelvo a iterar el fk de la clase subject para tener las notas
    //y la mapeo al dto de grades con un list ma
    public DTOGradesGetStudent getGrades(Authentication authentication){
        StudentsEntity studentsEntityQuery = studentRepository.findByInstitutionalId(authentication.getName())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontro el estudiante"));

        List<SubjectsEntity> subjectsEntityGet = studentsEntityQuery.getSubjectsEntities();
        Map<String, List<Double>> map = new HashMap<>();

        for (SubjectsEntity entity : subjectsEntityGet) {
            List<GradesEntity> gradesListaQuery = gradesRepositoy.findBySubjectsEntityIdAndStudentsEntityId(entity.getId(), studentsEntityQuery.getId())
                    .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
            if(gradesListaQuery.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Estudiante no tiene notas");
            List<Double> gradesListResponse = new ArrayList<>();
            for (GradesEntity gradesEntity: gradesListaQuery) {
                gradesListResponse.add(gradesEntity.getGrade());
            }
            map.put(entity.getSubjectName(), new ArrayList<>(gradesListResponse));
            gradesListResponse.remove(0);
        }

        DTOGradesGetStudent dtoGradesResponse = new DTOGradesGetStudent();
        dtoGradesResponse.setName(studentsEntityQuery.getName());
        dtoGradesResponse.setGrades(map);


        return dtoGradesResponse;
    }
}
