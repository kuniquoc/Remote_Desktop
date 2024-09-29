package quochung.server.payload.studymethod;

import lombok.Getter;
import lombok.Setter;
import quochung.server.model.schedule.SubjectType;

@Getter
@Setter
public class StudyMethodElementDto {

    private Long id;

    private String name;

    private String description;

    private String thumbnail;

    private SubjectType type;

}
