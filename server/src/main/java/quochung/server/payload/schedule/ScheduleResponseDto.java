package quochung.server.payload.schedule;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import quochung.server.model.schedule.Recurrence;
import quochung.server.model.schedule.SubjectType;
import quochung.server.model.studymethod.StudyMethod;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;

    private String title;

    private String description;

    private String location;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private String color;

    private Set<SubjectType> types = new HashSet<>();

    private Set<StudyMethod> studyMethods = new HashSet<>();

    @Embedded
    private Recurrence recurrence;

    private Set<Long> reminders = new HashSet<>();

    private String notes;
}
