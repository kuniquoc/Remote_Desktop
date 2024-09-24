package quochung.server.payload.studymethod;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudyMethodDto {
    private Long id;
    private String name;
    private String description;

    public StudyMethodDto(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
