package fav.com.classroomapp.DAO;

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
public class DTOTeacher {
    @NotNull
    @NotBlank
    private String subject;
    @NotNull
    private Integer studentCount;
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
}
