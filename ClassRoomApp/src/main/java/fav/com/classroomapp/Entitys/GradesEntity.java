package fav.com.classroomapp.Entitys;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class GradesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private double grade;
    @ManyToOne
    @JoinColumn(name = "subject_entity_id")
    private SubjectsEntity subjectsEntity;
    @ManyToOne
    @JoinColumn(name = "student_entity_id")
    private StudentsEntity studentsEntity;

}
