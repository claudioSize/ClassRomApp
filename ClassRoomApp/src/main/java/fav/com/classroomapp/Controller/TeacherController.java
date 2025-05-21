package fav.com.classroomapp.Controller;


import fav.com.classroomapp.DAO.DTOCreateGrade;
import fav.com.classroomapp.DAO.DTOModify;
import fav.com.classroomapp.DAO.DTOTeacher;
import fav.com.classroomapp.Security.JwtUtil;
import fav.com.classroomapp.Service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/profesor/")
public class TeacherController {
    @Autowired
    CreateTeacherService teacherService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    CreateGradesService createGradesService;
    @Autowired
    ModifyTeacherService modifyTeacherService;
    @Autowired
    GetAllGradeTeacher getAllGradeTeacher;

    @PostMapping("/register/")
    public ResponseEntity<HttpStatus> creaT(@Valid @RequestBody DTOTeacher dto){
        teacherService.createTe(dto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PostMapping("/calificar/")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<HttpStatus> createG(Authentication authentication, @Valid @RequestBody DTOCreateGrade dto){
        createGradesService.createGrade(authentication,dto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
    @PatchMapping("/modificar/data/")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<HttpStatus> modifyT(Authentication authentication, @Valid @RequestBody DTOModify dto){
        modifyTeacherService.modifyTeacher(authentication, dto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
    @GetMapping("/notas/")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<Map<String, List<Double>>> getGR(Authentication authentication){

        return ResponseEntity.ok(getAllGradeTeacher.getGrades(authentication));
    }

}
