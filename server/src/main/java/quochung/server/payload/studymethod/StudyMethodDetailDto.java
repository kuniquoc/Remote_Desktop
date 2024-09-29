package quochung.server.payload.studymethod;

import lombok.Getter;
import lombok.Setter;
import quochung.server.model.schedule.SubjectType;

@Getter
@Setter
public class StudyMethodDetailDto {

    private Long id;

    private String name;

    private String description;

    private String thumbnail;

    private SubjectType type;

    private String detail;

}
