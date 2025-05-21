package fav.com.classroomapp.Repository;

import fav.com.classroomapp.Entitys.GradesEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradesRepositoy extends JpaRepository<GradesEntity, Integer> {
    Optional<List<GradesEntity>> findBySubjectsEntityIdAndStudentsEntityId(Integer subId,Integer stuId);
}
