package quochung.server.model.schedule;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import quochung.server.model.schedule.enumtype.SubjectTypeName;
import quochung.server.model.studymethod.StudyMethod;

@Entity
@Table(name = "subject_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SubjectTypeName name;

    @OneToMany(mappedBy = "type")
    private Set<StudyMethod> study_methods = new HashSet<>();
}
