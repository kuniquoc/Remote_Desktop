package quochung.server.payload.schedule;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import quochung.server.model.schedule.enumtype.SubjectTypeName;

@Getter
@Setter
public class SubjectTypeDto {
    private Long id;

    @Enumerated(EnumType.STRING)
    private SubjectTypeName name;
}
