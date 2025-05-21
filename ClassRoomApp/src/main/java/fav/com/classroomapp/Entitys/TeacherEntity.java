package fav.com.classroomapp.Entitys;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor  // Lombok genera un constructor sin argumentos
@AllArgsConstructor  // Lombok genera un constructor con todos los campos
@Entity
@Builder
public class TeacherEntity extends UserBase{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer studentCount;
    @OneToMany(mappedBy = "teacherEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<StudentsEntity> studentsEntity;
    @OneToOne
    @JoinColumn(name = "subject_entity_id")
    private SubjectsEntity subjectsEntity;
}
