package fav.com.classroomapp.Controller;


import fav.com.classroomapp.DAO.DTOGradesGetStudent;
import fav.com.classroomapp.DAO.DTOModify;
import fav.com.classroomapp.DAO.DTOStudent;
import fav.com.classroomapp.Security.JwtUtil;
import fav.com.classroomapp.Service.GetStudentGradesService;
import fav.com.classroomapp.Service.CreateStudentService;
import fav.com.classroomapp.Service.ModifyStudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/estudiante/")
public class StrudentsController {
    @Autowired
    CreateStudentService studentService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    GetStudentGradesService gradesService;
    @Autowired
    ModifyStudentService modifyStudentService;

    @PostMapping("/register/")
    public ResponseEntity<HttpStatus> creatS(@Valid @RequestBody DTOStudent student){
        studentService.createStudents(student);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/notas/")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<DTOGradesGetStudent> getGrades(Authentication authentication){
        return ResponseEntity.ok(gradesService.getGrades(authentication));
    }
    @PatchMapping("/modificar/data/")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<HttpStatus> modifyT(Authentication authentication, @Valid @RequestBody DTOModify dto){
        modifyStudentService.modify(authentication, dto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

}
