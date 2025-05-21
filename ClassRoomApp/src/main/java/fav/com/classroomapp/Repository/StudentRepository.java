package fav.com.classroomapp.Repository;

import fav.com.classroomapp.Entitys.StudentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface StudentRepository extends JpaRepository<StudentsEntity, Integer> {
    boolean existsByInstitutionalId(String id);
    Optional<StudentsEntity> findByInstitutionalId(String id);

}
