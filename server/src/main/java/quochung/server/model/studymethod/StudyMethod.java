package quochung.server.model.studymethod;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import quochung.server.model.schedule.SubjectType;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "study_methods")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudyMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "subject_type_id")
    private SubjectType type;

    private String thumbnail;

    private String description;
}
