package fav.com.classroomapp.Entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AdminEntity extends UserBase{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
}
