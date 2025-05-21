package fav.com.classroomapp.Repository;

import fav.com.classroomapp.Entitys.AdminEntity;
import fav.com.classroomapp.Entitys.StudentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
    boolean existsByInstitutionalId(String id);
    Optional<AdminEntity> findAllByInstitutionalId(String id);
}
