package quochung.server.controller.schedule;

import quochung.server.model.schedule.Reminder;
import quochung.server.service.schedule.ReminderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reminders")
public class ReminderController {

    @Autowired
    private ReminderService reminderService;

    @PostMapping
    public ResponseEntity<Reminder> createReminder(@RequestBody Reminder reminder) {
        Reminder createdReminder = reminderService.createReminder(reminder);
        return ResponseEntity.ok(createdReminder);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<List<Reminder>> getRemindersByScheduleId(@PathVariable Long scheduleId) {
        List<Reminder> reminders = reminderService.getRemindersByScheduleId(scheduleId);
        return ResponseEntity.ok(reminders);
    }
}
