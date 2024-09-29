package quochung.server.payload.schedule;

import lombok.Getter;
import lombok.Setter;
import quochung.server.model.schedule.enumtype.ReminderMethod;

@Getter
@Setter
public class ReminderDto {
    private Long id;

    private ReminderMethod method;

    private int minutesBefore;
}
