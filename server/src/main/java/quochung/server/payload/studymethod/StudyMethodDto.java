package quochung.server.payload.studymethod;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import quochung.server.model.StudyMethodType;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudyMethodDto {
    private Long id;
    private String name;
    private StudyMethodType type;
    private String thumbnail;

    public StudyMethodDto(String name, StudyMethodType type, String thumbnail) {
        this.name = name;
        this.type = type;
        this.thumbnail = thumbnail;
    }
}
