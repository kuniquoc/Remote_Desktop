package quochung.server.model.schedule;

import java.time.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import quochung.server.model.schedule.enumtype.Frequency;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recurrence {

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    @JsonAlias("interval")
    private int interval_value;
    private List<DayOfWeek> DaysOfWeek;
    private LocalDate endRecurrence;
}
