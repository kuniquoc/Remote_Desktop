package quochung.server.payload.schedule;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;
import quochung.server.model.schedule.Recurrence;

@Getter
@Setter
public class ScheduleRequestDto {
    private Long id;
    
    private String title;

    private String description;

    private String location;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private String color;

    private Set<Long> typeIds = new HashSet<>();

    private Set<Long> studyMethodIds = new HashSet<>();

    @Embedded
    private Recurrence recurrence;

    private Set<ReminderDto> reminders = new HashSet<>();

    private String notes;

    public ScheduleRequestDto(String title, String description, String location, LocalDateTime startDateTime,
            LocalDateTime endDateTime, String color, Set<Long> typeIds, Set<Long> studyMethodIds,
            Set<ReminderDto> reminders,
            Recurrence recurrence, String notes) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.color = color;
        this.typeIds = typeIds;
        this.studyMethodIds = studyMethodIds;
        this.reminders = reminders;
        this.recurrence = recurrence;
        this.notes = notes;
    }
}
