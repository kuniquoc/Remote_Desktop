package quochung.server.payload.schedule;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;
import quochung.server.model.schedule.Recurrence;
import quochung.server.model.schedule.SubjectType;
import quochung.server.model.studymethod.StudyMethod;

@Getter
@Setter
public class ScheduleResponseDto {
    private Long id;

    private String title;

    private String description;

    private String location;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private String color;

    private List<SubjectType> types = new ArrayList<>();

    private List<StudyMethod> studyMethods = new ArrayList<>();

    @Embedded
    private Recurrence recurrence;

    private List<ReminderDto> reminders = new ArrayList<>();

    private String notes;
}
