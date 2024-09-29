package quochung.server.repository.schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import quochung.server.model.schedule.SubjectType;

import java.util.Set;

public interface SubjectTypeRepository extends JpaRepository<SubjectType, Long> {
    Set<SubjectType> findByIdIn(Set<Long> ids);
}
