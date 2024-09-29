package quochung.server.repository.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import quochung.server.model.schedule.Schedule;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByUserId(Long id);

    List<Schedule> findByUserIdAndStartDateTimeBetween(Long id, LocalDateTime start, LocalDateTime end);

}
