package quochung.server.payload.studymethod;


import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import quochung.server.model.schedule.SubjectType;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudyMethodDto {
    private Long id;
    private String name;
    private SubjectType type;
    private String thumbnail;

    public StudyMethodDto(String name, SubjectType type, String thumbnail) {
        this.name = name;
        this.type = type;
        this.thumbnail = thumbnail;
    }
}
