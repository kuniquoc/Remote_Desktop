package quochung.server.controller.studymethod;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import quochung.server.payload.MessageDto;
import quochung.server.payload.studymethod.StudyMethodDto;
import quochung.server.service.studymethod.StudyMethodService;

@RestController
@RequestMapping("/api/study-methods")
public class StudyMethodController {
    @Autowired
    private StudyMethodService studyMethodService;

    @PostMapping
    public ResponseEntity<?> createStudyMethod(@RequestBody StudyMethodDto studyMethodDto) {
        try {
            String message = "Study method created successfully";
            Object data = studyMethodService.createStudyMethod(studyMethodDto);
            return ResponseEntity.status(201).body(new MessageDto(message, data));
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return ResponseEntity.status(500).body(new MessageDto("Internal Server Error"));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllStudyMethods() {
        try {
            String message = "Study methods retrieved successfully";
            Object data = studyMethodService.getAllStudyMethods();
            return ResponseEntity.ok(new MessageDto(message, data));
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return ResponseEntity.status(500).body(new MessageDto("Internal Server Error"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudyMethodById(@PathVariable Long id) {
        try {
            String message = "Study method retrieved successfully";
            Object data = studyMethodService.getStudyMethodById(id);
            return ResponseEntity.ok(new MessageDto(message, data));
        } catch (BadRequestException e) {
            return ResponseEntity.status(400).body(new MessageDto(e.getMessage()));
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return ResponseEntity.status(500).body(new MessageDto("Internal Server Error"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudyMethod(@PathVariable Long id, @RequestBody StudyMethodDto studyMethodDto) {
        try {
            String message = "Study method updated successfully";
            Object data = studyMethodService.updateStudyMethod(id, studyMethodDto);
            return ResponseEntity.ok(new MessageDto(message, data));
        } catch (BadRequestException e) {
            return ResponseEntity.status(404).body(new MessageDto(e.getMessage()));
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return ResponseEntity.status(500).body(new MessageDto("Internal Server Error"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudyMethod(@PathVariable Long id) {
        try {
            studyMethodService.deleteStudyMethod(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return ResponseEntity.status(500).body(new MessageDto("Internal Server Error"));
        }
    }
}
