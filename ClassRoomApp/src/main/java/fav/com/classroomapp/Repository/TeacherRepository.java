package fav.com.classroomapp.Repository;

import fav.com.classroomapp.Entitys.StudentsEntity;
import fav.com.classroomapp.Entitys.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface TeacherRepository extends JpaRepository<TeacherEntity, Integer> {
    boolean existsByInstitutionalId(String id);

    Optional<TeacherEntity> findByInstitutionalId(String name);
}
