package quochung.server.payload.schedule;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleElementDto {
    private Long id;

    private String title;

    private String location;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private String color;

    private List<ReminderDto> reminders = new ArrayList<>();
}
