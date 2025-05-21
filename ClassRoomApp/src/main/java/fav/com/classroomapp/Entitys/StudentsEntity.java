package fav.com.classroomapp.Entitys;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class StudentsEntity extends UserBase{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String guardianName;
    private Integer guardianNumber;
    @ManyToOne
    @JoinColumn(name = "teacher_entity_id")
    private TeacherEntity teacherEntity;
    @ManyToMany
    @JoinTable(
            name = "students_subjects",
            joinColumns = @JoinColumn(name = "students_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "subjects_entity_id")
    )
    private List<SubjectsEntity> subjectsEntities;

    @OneToMany(mappedBy = "studentsEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<GradesEntity> gradesEntities;


}
