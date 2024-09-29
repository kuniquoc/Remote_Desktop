package quochung.server.controller.schedule;

import quochung.server.payload.MessageDto;
import quochung.server.payload.schedule.ScheduleRequestDto;
import quochung.server.service.schedule.ScheduleService;

import java.util.HashMap;
import java.util.Map;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<?> createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        try {
            String message = "Lịch học được tạo thành công";
            Long id = scheduleService.createSchedule(scheduleRequestDto);
            Map<String, Long> data = new HashMap<>();
            data.put("id", id);

            return ResponseEntity.status(201).body(new MessageDto(message, data));
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return ResponseEntity.status(500).body(new MessageDto("Lỗi server"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getScheduleById(@PathVariable Long id) {
        try {
            String message = "Lấy thông tin lịch học thành công";
            Object data = scheduleService.getScheduleById(id);
            return ResponseEntity.ok(new MessageDto(message, data));
        } catch (BadRequestException e) {
            return ResponseEntity.status(400).body(new MessageDto(e.getMessage()));
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return ResponseEntity.status(500).body(new MessageDto("Lỗi server"));
        }
    }

    @GetMapping
    public ResponseEntity<?> getSchedules(@RequestParam(defaultValue = "month") String mode) {
        try {
            String message = "Lấy danh sách lịch học thành công";
            Object data = scheduleService.getSchedules(mode);
            return ResponseEntity.ok(new MessageDto(message, data));
        } catch (BadRequestException e) {
            return ResponseEntity.status(400).body(new MessageDto(e.getMessage()));
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return ResponseEntity.status(500).body(new MessageDto("Lỗi server"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto scheduleRequestDto) {
        try {
            String message = "Cập nhật lịch học thành công";
            scheduleService.updateSchedule(id, scheduleRequestDto);
            return ResponseEntity.ok(new MessageDto(message));
        } catch (BadRequestException e) {
            return ResponseEntity.status(400).body(new MessageDto(e.getMessage()));
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return ResponseEntity.status(500).body(new MessageDto("Lỗi server"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long id) {
        try {
            scheduleService.deleteSchedule(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return ResponseEntity.status(500).body(new MessageDto("Lỗi server"));
        }
    }
}
