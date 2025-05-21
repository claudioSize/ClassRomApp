package fav.com.classroomapp.Service;

import fav.com.classroomapp.DAO.DTOSubject;
import fav.com.classroomapp.Entitys.GradesEntity;
import fav.com.classroomapp.Entitys.SubjectsEntity;
import fav.com.classroomapp.Repository.GradesRepositoy;
import fav.com.classroomapp.Repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateSubjectService {
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    GradesRepositoy gradesRepositoy;

    public void createSubject(DTOSubject dto){
        SubjectsEntity subjectsEntity = new SubjectsEntity();
        subjectsEntity.setSubjectName(dto.getSubjectName());
        subjectRepository.save(subjectsEntity);
    }
}
