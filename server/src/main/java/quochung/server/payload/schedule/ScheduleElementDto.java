package quochung.server.payload.schedule;

import java.time.LocalDateTime;
import java.util.Set;
import quochung.server.model.schedule.Reminder;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleElementDto {
    private Long id;

    private String title;

    private String location;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private String color;

    private Set<Reminder> reminders;
}
