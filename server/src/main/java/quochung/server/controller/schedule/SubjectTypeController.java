package quochung.server.controller.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import quochung.server.payload.MessageDto;
import quochung.server.service.schedule.SubjectTypeService;

@RestController
@RequestMapping("/api/subject-types")
public class SubjectTypeController {

    @Autowired
    private SubjectTypeService subjectTypeService;

    @GetMapping
    public ResponseEntity<?> getAllSubjectTypes() {
        try {
            var message = "Lấy danh sách loại môn học thành công";
            var data = subjectTypeService.getAllSubjectTypes();
            return ResponseEntity.ok(new MessageDto(message, data));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(500).body(new MessageDto("Lỗi khi lấy danh sách loại môn học"));
        }
    }
}