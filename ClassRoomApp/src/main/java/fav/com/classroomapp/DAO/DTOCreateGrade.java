package fav.com.classroomapp.DAO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DTOCreateGrade {
    @NotNull
    @NotBlank
    private String institutionalId;
    @NotNull
    private Double grade;
}
