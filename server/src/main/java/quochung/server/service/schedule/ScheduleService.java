package quochung.server.service.schedule;

import quochung.server.repository.schedule.ReminderRepository;
import quochung.server.repository.schedule.ScheduleRepository;
import quochung.server.repository.schedule.SubjectTypeRepository;
import quochung.server.repository.studymethod.StudyMethodRepository;
import quochung.server.service.user.UserDetailsServiceImplement;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import quochung.server.model.schedule.Schedule;
import quochung.server.model.user.User;
import quochung.server.payload.schedule.ScheduleRequestDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private SubjectTypeRepository subjectTypeRepository;

    @Autowired
    private StudyMethodRepository studyMethodRepository;

    @Autowired
    private ReminderRepository reminderRepository;

    @Autowired
    private UserDetailsServiceImplement UserDetailsServiceImplement;

    public Schedule createSchedule(ScheduleRequestDto scheduleDto) {
        User user = UserDetailsServiceImplement.getCurrentUser();

        Schedule newSchedule = new Schedule();
        newSchedule.setUser(user);
        newSchedule.setTitle(scheduleDto.getTitle());
        newSchedule.setDescription(scheduleDto.getDescription());
        newSchedule.setLocation(scheduleDto.getLocation());
        newSchedule.setStartDateTime(scheduleDto.getStartDateTime());
        newSchedule.setEndDateTime(scheduleDto.getEndDateTime());
        newSchedule.setColor(scheduleDto.getColor());
        newSchedule.setTypes(subjectTypeRepository.findByIdIn(scheduleDto.getTypeIds()));
        newSchedule.setStudyMethods(studyMethodRepository.findByIdIn(scheduleDto.getStudyMethodIds()));
        newSchedule.setRecurrence(scheduleDto.getRecurrence());
        newSchedule.setReminders(reminderRepository.findByIdIn(scheduleDto.getReminderIds()));
        newSchedule.setNotes(scheduleDto.getNotes());
        return scheduleRepository.save(newSchedule);
    }

    public List<Schedule> getSchedules(String mode) throws BadRequestException {
        Long userId = UserDetailsServiceImplement.getCurrentUserId();
        LocalDateTime startTime = LocalDateTime.now();

        LocalDateTime endTime;

        if (mode.equals("week")) {
            endTime = LocalDateTime.now().plusDays(7);
        } else if (mode.equals("month")) {
            endTime = LocalDateTime.now().plusMonths(1);
        } else if (mode.equals("year")) {
            endTime = LocalDateTime.now().plusYears(1);
        } else {
            throw new BadRequestException("Không có chế độ nào phù hợp");
        }

        return scheduleRepository.findByUserIdAndStartDateTimeBetween(userId, startTime, endTime);
    }

    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() -> new RuntimeException("Schedule not found"));
    }

    public Schedule updateSchedule(Long id, Schedule schedule) {
        Schedule existingSchedule = getScheduleById(id);
        existingSchedule.setTitle(schedule.getTitle());
        existingSchedule.setDescription(schedule.getDescription());
        existingSchedule.setLocation(schedule.getLocation());
        existingSchedule.setStartDateTime(schedule.getStartDateTime());
        existingSchedule.setEndDateTime(schedule.getEndDateTime());
        existingSchedule.setColor(schedule.getColor());
        existingSchedule.setTypes(schedule.getTypes());
        existingSchedule.setStudyMethods(schedule.getStudyMethods());
        existingSchedule.setRecurrence(schedule.getRecurrence());
        existingSchedule.setReminders(schedule.getReminders());
        existingSchedule.setNotes(schedule.getNotes());
        return scheduleRepository.save(existingSchedule);
    }

    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}
