package quochung.server.service.studymethod;

import org.springframework.stereotype.Service;

import quochung.server.model.studymethod.StudyMethod;
import quochung.server.payload.studymethod.*;
import quochung.server.repository.studymethod.StudyMethodRepository;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class StudyMethodService {
    @Autowired
    private StudyMethodRepository studyMethodRepository;

    public StudyMethod createStudyMethod(StudyMethodDto studyMethodDto) {
        StudyMethod studyMethod = new StudyMethod();
        studyMethod.setName(studyMethodDto.getName());
        studyMethod.setType(studyMethodDto.getType());
        studyMethod.setThumbnail(studyMethodDto.getThumbnail());
        return studyMethodRepository.save(studyMethod);
    }

    public List<StudyMethod> getAllStudyMethods() {
        return studyMethodRepository.findAll();
    }

    public StudyMethod getStudyMethodById(Long id) throws BadRequestException {
        return studyMethodRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Phương pháp học không tồn tại"));
    }

    public StudyMethod updateStudyMethod(Long id, StudyMethodDto studyMethodDto) throws BadRequestException {
        StudyMethod studyMethod = studyMethodRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Phương pháp học không tồn tại"));
        studyMethod.setName(studyMethodDto.getName());
        studyMethod.setType(studyMethodDto.getType());
        studyMethod.setThumbnail(studyMethodDto.getThumbnail());
        return studyMethodRepository.save(studyMethod);
    }

    public void deleteStudyMethod(Long id) {
        studyMethodRepository.deleteById(id);
    }
}
