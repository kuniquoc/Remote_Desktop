package quochung.server.service.schedule;

import quochung.server.model.schedule.Reminder;
import quochung.server.repository.schedule.ReminderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReminderService {

    @Autowired
    private ReminderRepository reminderRepository;

    public Reminder createReminder(Reminder reminder) {
        return reminderRepository.save(reminder);
    }

    public List<Reminder> getRemindersByScheduleId(Long scheduleId) {
        return reminderRepository.findByScheduleId(scheduleId);
    }
}
