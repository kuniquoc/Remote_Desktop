package quochung.server.payload.studymethod;

import java.util.Set;

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
    private Set<SubjectType> types;
    private String thumbnail;

    public StudyMethodDto(String name, Set<SubjectType> types, String thumbnail) {
        this.name = name;
        this.types = types;
        this.thumbnail = thumbnail;
    }
}
