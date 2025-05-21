package fav.com.classroomapp.Controller;

import fav.com.classroomapp.Security.DTODtails;
import fav.com.classroomapp.Security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home/")
public class HomeController {
    @Autowired
    JwtUtil jwtUtil;


    @PostMapping("/login/")
    public ResponseEntity<String> loginT(@Valid @RequestBody DTODtails dto){

        return ResponseEntity.ok(jwtUtil.buildToken(dto));
    }
}
