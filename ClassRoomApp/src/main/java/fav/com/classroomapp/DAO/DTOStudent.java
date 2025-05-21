package fav.com.classroomapp.DAO;


import fav.com.classroomapp.Entitys.UserBase;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DTOStudent {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String lasName;
    @NotNull
    @NotBlank
    private String institutionalId;
    @NotNull
    @NotBlank
    private String password;
    @NotNull
    @NotBlank
    private String guardianName;
    @NotNull
    private Integer guardianNumber;
    @NotNull
    @NotBlank
    private String teacherInstitutionalId;
}
