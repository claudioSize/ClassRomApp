package fav.com.classroomapp.Repository;

import fav.com.classroomapp.Entitys.SubjectsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectsEntity, Integer> {

    Optional<SubjectsEntity> findBySubjectName(String subject);
}
