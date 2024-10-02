package quochung.server.service.schedule;

import org.springframework.stereotype.Service;

import quochung.server.repository.schedule.SubjectTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import quochung.server.payload.schedule.SubjectTypeDto;

@Service
public class SubjectTypeService {
    @Autowired
    private SubjectTypeRepository subjectTypeRepository;

    public List<SubjectTypeDto> getAllSubjectTypes() {
        return subjectTypeRepository.findAll().stream().map(subjectType -> {
            var subjectTypeDto = new SubjectTypeDto();
            subjectTypeDto.setId(subjectType.getId());
            subjectTypeDto.setName(subjectType.getName());
            return subjectTypeDto;
        }).toList();
    }
}
