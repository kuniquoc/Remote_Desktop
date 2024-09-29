package quochung.server.service.studymethod;

import org.springframework.stereotype.Service;

import quochung.server.model.studymethod.StudyMethod;
import quochung.server.payload.studymethod.*;
import quochung.server.repository.studymethod.StudyMethodRepository;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@Service
public class StudyMethodService {
    @Autowired
    private StudyMethodRepository studyMethodRepository;

    public StudyMethod createStudyMethod(StudyMethodDetailDto createStudyMethodRequest) {
        StudyMethod studyMethod = new StudyMethod();
        studyMethod.setName(createStudyMethodRequest.getName());
        studyMethod.setDescription(createStudyMethodRequest.getDescription());
        studyMethod.setThumbnail(createStudyMethodRequest.getThumbnail());
        studyMethod.setType(createStudyMethodRequest.getType());
        studyMethod.setDetail(createStudyMethodRequest.getDetail());
        return studyMethodRepository.save(studyMethod);
    }

    public List<StudyMethodElementDto> getAllStudyMethods(int page, int size, String type) {
        PageRequest pageRequest = PageRequest.of(page, size);

        if (type == null) {
            return studyMethodRepository.findAll(pageRequest).map(
                    studyMethod -> {
                        StudyMethodElementDto studyMethodElementDto = new StudyMethodElementDto();
                        studyMethodElementDto.setId(studyMethod.getId());
                        studyMethodElementDto.setName(studyMethod.getName());
                        studyMethodElementDto.setDescription(studyMethod.getDescription());
                        studyMethodElementDto.setThumbnail(studyMethod.getThumbnail());
                        studyMethodElementDto.setType(studyMethod.getType());
                        return studyMethodElementDto;
                    }).toList();
        } else {
            return studyMethodRepository.findByType(type, pageRequest).map(
                    studyMethod -> {
                        StudyMethodElementDto studyMethodElementDto = new StudyMethodElementDto();
                        studyMethodElementDto.setId(studyMethod.getId());
                        studyMethodElementDto.setName(studyMethod.getName());
                        studyMethodElementDto.setDescription(studyMethod.getDescription());
                        studyMethodElementDto.setThumbnail(studyMethod.getThumbnail());
                        studyMethodElementDto.setType(studyMethod.getType());
                        return studyMethodElementDto;
                    }).toList();
        }
    }

    public StudyMethodDetailDto getStudyMethodById(Long id) throws BadRequestException {
        StudyMethod studyMethod = studyMethodRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Không tìm thấy phương pháp học"));
        StudyMethodDetailDto studyMethodDetailDto = new StudyMethodDetailDto();
        studyMethodDetailDto.setId(studyMethod.getId());
        studyMethodDetailDto.setName(studyMethod.getName());
        studyMethodDetailDto.setDescription(studyMethod.getDescription());
        studyMethodDetailDto.setThumbnail(studyMethod.getThumbnail());
        studyMethodDetailDto.setType(studyMethod.getType());
        studyMethodDetailDto.setDetail(studyMethod.getDetail());
        return studyMethodDetailDto;
    }

    public StudyMethod updateStudyMethod(Long id, StudyMethodDetailDto updateStudyMethodRequest)
            throws BadRequestException {
        StudyMethod studyMethod = studyMethodRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Không tìm thấy phương pháp học"));
        studyMethod.setName(updateStudyMethodRequest.getName());
        studyMethod.setDescription(updateStudyMethodRequest.getDescription());
        studyMethod.setThumbnail(updateStudyMethodRequest.getThumbnail());
        studyMethod.setType(updateStudyMethodRequest.getType());
        studyMethod.setDetail(updateStudyMethodRequest.getDetail());
        return studyMethodRepository.save(studyMethod);
    }

    public void deleteStudyMethod(Long id) {
        studyMethodRepository.deleteById(id);
    }
}
