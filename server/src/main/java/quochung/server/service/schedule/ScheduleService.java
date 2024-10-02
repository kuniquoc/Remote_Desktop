package quochung.server.service.schedule;

import quochung.server.repository.schedule.ReminderRepository;
import quochung.server.repository.schedule.ScheduleRepository;
import quochung.server.repository.schedule.SubjectTypeRepository;
import quochung.server.repository.studymethod.StudyMethodRepository;
import quochung.server.service.user.UserDetailsServiceImplement;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quochung.server.model.schedule.Reminder;
import quochung.server.model.schedule.Schedule;
import quochung.server.model.user.User;
import quochung.server.payload.schedule.ReminderDto;
import quochung.server.payload.schedule.ScheduleElementDto;
import quochung.server.payload.schedule.ScheduleRequestDto;
import quochung.server.payload.schedule.ScheduleResponseDto;

import java.time.DayOfWeek;
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

    public Long createSchedule(ScheduleRequestDto scheduleDto) {
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
        newSchedule.setNotes(scheduleDto.getNotes());

        scheduleRepository.save(newSchedule);

        for (ReminderDto reminder : scheduleDto.getReminders()) {
            Reminder newReminder = new Reminder();
            newReminder.setMethod(reminder.getMethod());
            newReminder.setMinutesBefore(reminder.getMinutesBefore());
            newReminder.setSchedule(newSchedule);
            reminderRepository.save(newReminder);
        }

        return newSchedule.getId();
    }

    public ScheduleResponseDto getScheduleById(Long id) throws BadRequestException {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Không tìm thấy lịch học"));

        ScheduleResponseDto scheduleDto = new ScheduleResponseDto();
        scheduleDto.setId(schedule.getId());
        scheduleDto.setTitle(schedule.getTitle());
        scheduleDto.setDescription(schedule.getDescription());
        scheduleDto.setLocation(schedule.getLocation());
        scheduleDto.setStartDateTime(schedule.getStartDateTime());
        scheduleDto.setEndDateTime(schedule.getEndDateTime());
        scheduleDto.setColor(schedule.getColor());
        scheduleDto.setTypes(schedule.getTypes().stream().toList());
        scheduleDto.setStudyMethods(schedule.getStudyMethods().stream().toList());
        scheduleDto.setRecurrence(schedule.getRecurrence());
        scheduleDto.setReminders(schedule.getReminders().stream().map(reminder -> {
            ReminderDto reminderDto = new ReminderDto();
            reminderDto.setId(reminder.getId());
            reminderDto.setMethod(reminder.getMethod());
            reminderDto.setMinutesBefore(reminder.getMinutesBefore());
            return reminderDto;
        }).toList());
        scheduleDto.setNotes(schedule.getNotes());
        return scheduleDto;
    }

    public LocalDate previousSunday(LocalDate date) {
        if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return date;
        }
        return date.with(DayOfWeek.SUNDAY).minusDays(7);
    }

    public List<ScheduleElementDto> getSchedules(String mode, LocalDate viewDate) throws BadRequestException {
        Long userId = UserDetailsServiceImplement.getCurrentUser().getId();

        LocalDateTime startTime;
        LocalDateTime endTime;

        if (mode.equals("week")) {
            startTime = previousSunday(viewDate).atStartOfDay();
            endTime = startTime.plusDays(7).minusSeconds(1);
        } else if (mode.equals("month")) {
            startTime = previousSunday(viewDate.withDayOfMonth(1)).atStartOfDay();
            endTime = startTime.plusMonths(1).minusSeconds(1);
        } else if (mode.equals("year")) {
            startTime = previousSunday(viewDate.withDayOfYear(1)).atStartOfDay();
            endTime = startTime.plusYears(1).minusSeconds(1);
        } else {
            throw new BadRequestException("Không có chế độ nào phù hợp");
        }

        return scheduleRepository.findByUserIdAndStartDateTimeBetween(userId, startTime, endTime).stream()
                .map(schedule -> {
                    ScheduleElementDto scheduleElementDto = new ScheduleElementDto();
                    scheduleElementDto.setId(schedule.getId());
                    scheduleElementDto.setTitle(schedule.getTitle());
                    scheduleElementDto.setLocation(schedule.getLocation());
                    scheduleElementDto.setStartDateTime(schedule.getStartDateTime());
                    scheduleElementDto.setEndDateTime(schedule.getEndDateTime());
                    scheduleElementDto.setColor(schedule.getColor());
                    scheduleElementDto.setReminders(schedule.getReminders().stream().map(reminder -> {
                        ReminderDto reminderDto = new ReminderDto();
                        reminderDto.setId(reminder.getId());
                        reminderDto.setMethod(reminder.getMethod());
                        reminderDto.setMinutesBefore(reminder.getMinutesBefore());
                        return reminderDto;
                    }).toList());
                    return scheduleElementDto;
                }).toList();
    }

    public void updateSchedule(Long id, ScheduleRequestDto scheduleDto) throws BadRequestException {
        Schedule existingSchedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Không tìm thấy lịch học"));

        existingSchedule.setTitle(scheduleDto.getTitle());
        existingSchedule.setDescription(scheduleDto.getDescription());
        existingSchedule.setLocation(scheduleDto.getLocation());
        existingSchedule.setStartDateTime(scheduleDto.getStartDateTime());
        existingSchedule.setEndDateTime(scheduleDto.getEndDateTime());
        existingSchedule.setColor(scheduleDto.getColor());
        existingSchedule.setTypes(subjectTypeRepository.findByIdIn(scheduleDto.getTypeIds()));
        existingSchedule.setStudyMethods(studyMethodRepository.findByIdIn(scheduleDto.getStudyMethodIds()));
        existingSchedule.setRecurrence(scheduleDto.getRecurrence());

        existingSchedule.getReminders().clear();
        for (ReminderDto reminder : scheduleDto.getReminders()) {
            Reminder newReminder = new Reminder();
            newReminder.setMethod(reminder.getMethod());
            newReminder.setMinutesBefore(reminder.getMinutesBefore());
            newReminder.setSchedule(existingSchedule);
            reminderRepository.save(newReminder);
        }

        existingSchedule.setNotes(scheduleDto.getNotes());
        scheduleRepository.save(existingSchedule);
    }

    public void deleteSchedule(Long id) throws BadRequestException {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Không tìm thấy lịch học"));
        reminderRepository.deleteAll(schedule.getReminders());
        scheduleRepository.delete(schedule);
    }
}
