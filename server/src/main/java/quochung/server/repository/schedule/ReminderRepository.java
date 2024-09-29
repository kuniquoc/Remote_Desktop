package quochung.server.repository.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import quochung.server.model.schedule.Reminder;

import java.util.Set;
import java.util.List;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    List<Reminder> findByScheduleId(Long scheduleId);

    Set<Reminder> findByIdIn(Set<Long> ids);
}
