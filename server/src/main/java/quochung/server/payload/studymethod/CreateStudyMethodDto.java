package quochung.server.payload.studymethod;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStudyMethodDto {

    private Long id;

    private String name;

    private String description;

    private String thumbnail;

    private Long typeId;

    private String detail;

}
