package fav.com.classroomapp.Repository;

import fav.com.classroomapp.Security.UserDtails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDtails, Integer> {
    Optional<UserDtails> findByInstitutionalId(String userName);
    boolean existsByInstitutionalId(String id);

}
