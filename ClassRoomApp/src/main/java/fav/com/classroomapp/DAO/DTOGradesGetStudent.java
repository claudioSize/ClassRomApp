package fav.com.classroomapp.DAO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DTOGradesGetStudent {
    private String name;
    private Map<String, List<Double>> grades;

}
