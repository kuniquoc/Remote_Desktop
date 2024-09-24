package quochung.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import quochung.server.model.StudyMethod;

@Repository
public interface StudyMethodRepository extends JpaRepository<StudyMethod, Long> {
}
