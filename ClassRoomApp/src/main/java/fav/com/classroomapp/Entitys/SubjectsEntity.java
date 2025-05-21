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
public class SubjectsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String subjectName;

    @OneToOne(mappedBy = "subjectsEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private TeacherEntity teacherEntity;

    @ManyToMany(mappedBy = "subjectsEntities")
    private List<StudentsEntity> studentsEntities;

    @OneToMany(mappedBy = "subjectsEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<GradesEntity> gradesEntities;
}
