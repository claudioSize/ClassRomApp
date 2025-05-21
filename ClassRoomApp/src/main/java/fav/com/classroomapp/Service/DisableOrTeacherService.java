package fav.com.classroomapp.Service;

import fav.com.classroomapp.DAO.DTOGetInstitutionalId;
import fav.com.classroomapp.Repository.UserDetailsRepository;
import fav.com.classroomapp.Security.UserDtails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DisableOrTeacherService {
    @Autowired
    UserDetailsRepository userDetailsRepository;
    public void disableAccount(DTOGetInstitutionalId dto){
        UserDtails userDtails = userDetailsRepository.findByInstitutionalId(dto.getInstitutionalId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Profesor no encontrado"));
        userDtails.setEnabled(false);
        userDetailsRepository.save(userDtails);
    }
    public void enableAccount(DTOGetInstitutionalId dto){
        UserDtails userDtails = userDetailsRepository.findByInstitutionalId(dto.getInstitutionalId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Profesor no encontrado"));
        userDtails.setEnabled(true);
        userDetailsRepository.save(userDtails);
    }

}
