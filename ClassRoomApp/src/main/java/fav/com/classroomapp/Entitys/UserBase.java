package fav.com.classroomapp.Entitys;

import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.util.Date;
@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class UserBase {
    private String name;
    private String lasName;
    private String institutionalId;
    private String password;
    private Date creationDate;
}
