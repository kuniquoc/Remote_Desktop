package quochung.server.controller.schedule;

import quochung.server.model.schedule.Schedule;
import quochung.server.payload.schedule.ScheduleRequestDto;
import quochung.server.service.schedule.ScheduleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<Schedule> createSchedule(@RequestBody ScheduleRequestDto schedule) {
        Schedule createdSchedule = scheduleService.createSchedule(schedule);
        return ResponseEntity.ok(createdSchedule);
    }

    // @GetMapping
    // public ResponseEntity<List<Schedule>> getSchedules(@RequestParam(required =
    // false) Integer month,
    // @RequestParam(required = false) Integer year) {
    // List<Schedule> schedules;
    // if (month != null && year != null) {
    // schedules = scheduleService.getSchedulesByMonthAndYear(month, year);
    // } else {
    // schedules = scheduleService.getAllSchedules();
    // }
    // return ResponseEntity.ok(schedules);
    // }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable Long id) {
        Schedule schedule = scheduleService.getScheduleById(id);
        return ResponseEntity.ok(schedule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long id, @RequestBody Schedule schedule) {
        Schedule updatedSchedule = scheduleService.updateSchedule(id, schedule);
        return ResponseEntity.ok(updatedSchedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
}
