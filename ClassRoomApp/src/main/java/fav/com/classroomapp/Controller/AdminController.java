package fav.com.classroomapp.Controller;

import fav.com.classroomapp.DAO.DTOAdmin;
import fav.com.classroomapp.DAO.DTOGetInstitutionalId;
import fav.com.classroomapp.DAO.DTOModify;
import fav.com.classroomapp.DAO.DTOSubject;
import fav.com.classroomapp.Security.JwtUtil;
import fav.com.classroomapp.Service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {
    @Autowired
    CreateAdminService adminService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    CreateSubjectService subjectService;
    @Autowired
    DisableOrTeacherService disableTeacherService;
    @Autowired
    ModifyAdminService modifyAdminService;

    @PostMapping("/register/")
    public ResponseEntity<HttpStatus> createA(@Valid @RequestBody DTOAdmin dto){
        adminService.createA(dto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PostMapping("/nueva/materia/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> creteSu(@Valid @RequestBody DTOSubject dto){
        subjectService.createSubject(dto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PostMapping("/inhabilitar/profesor/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> inTechear(@Valid @RequestBody DTOGetInstitutionalId dto){
        disableTeacherService.disableAccount(dto);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/habilitar/profesor/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> enTechear(@Valid @RequestBody DTOGetInstitutionalId dto){
        disableTeacherService.enableAccount(dto);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/modificar/data/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> modifyData(Authentication authentication, @Valid @RequestBody DTOModify dto){
        modifyAdminService.modifyDataMe(authentication,dto);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }


}
