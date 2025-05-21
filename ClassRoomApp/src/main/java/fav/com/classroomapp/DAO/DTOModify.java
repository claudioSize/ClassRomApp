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
public class DTOModify {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String lasName;
    @NotNull
    @NotBlank
    private String password;
}
