package quochung.server.controller.studymethod;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import quochung.server.payload.MessageDto;
import quochung.server.payload.studymethod.StudyMethodDetailDto;
import quochung.server.service.studymethod.StudyMethodService;
import quochung.server.util.HtmlSanitizer;

@RestController
@RequestMapping("/api/study-methods")
public class StudyMethodController {
    @Autowired
    private StudyMethodService studyMethodService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createStudyMethod(@RequestBody StudyMethodDetailDto studyMethodDetailDto) {
        try {
            studyMethodDetailDto.setDetail(HtmlSanitizer.escapeHtml(studyMethodDetailDto.getDetail()));

            String message = "Phương pháp học được tạo thành công";
            Object data = studyMethodService.createStudyMethod(studyMethodDetailDto);
            return ResponseEntity.status(201).body(new MessageDto(message, data));
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return ResponseEntity.status(500).body(new MessageDto("Lỗi server"));
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_User')")
    public ResponseEntity<?> getAllStudyMethods(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size, @RequestParam(required = false) String type) {
        try {
            String message = "Lấy danh sách phương pháp học thành công";
            Object data = studyMethodService.getAllStudyMethods(page, size, type);
            return ResponseEntity.ok(new MessageDto(message, data));
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return ResponseEntity.status(500).body(new MessageDto("Lỗi server"));
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getStudyMethodById(@PathVariable Long id) {
        try {
            String message = "Lấy thông tin phương pháp học thành công";
            Object data = studyMethodService.getStudyMethodById(id);
            return ResponseEntity.ok(new MessageDto(message, data));
        } catch (BadRequestException e) {
            return ResponseEntity.status(400).body(new MessageDto(e.getMessage()));
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return ResponseEntity.status(500).body(new MessageDto("Lỗi server"));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateStudyMethod(@PathVariable Long id,
            @RequestBody StudyMethodDetailDto studyMethodDetailDto) {
        try {
            studyMethodDetailDto.setDetail(HtmlSanitizer.escapeHtml(studyMethodDetailDto.getDetail()));

            String message = "Cập nhật phương pháp học thành công";
            Object data = studyMethodService.updateStudyMethod(id, studyMethodDetailDto);
            return ResponseEntity.ok(new MessageDto(message, data));
        } catch (BadRequestException e) {
            return ResponseEntity.status(400).body(new MessageDto(e.getMessage()));
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return ResponseEntity.status(500).body(new MessageDto("Lỗi server"));
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteStudyMethod(@PathVariable Long id) {
        try {
            studyMethodService.deleteStudyMethod(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return ResponseEntity.status(500).body(new MessageDto("Lỗi server"));
        }
    }
}
