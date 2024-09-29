package quochung.server.model.schedule;

import java.time.*;
import java.util.*;
import jakarta.persistence.*;
import lombok.*;
import quochung.server.model.studymethod.StudyMethod;
import quochung.server.model.user.User;

@Entity
@Table(name = "schedules")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String title;

    private String description;

    private String location;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private String color;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "schedule_subject_types", joinColumns = @JoinColumn(name = "schedule_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "subject_type_id", referencedColumnName = "id"))
    private Set<SubjectType> types = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "schedule_study_methods", joinColumns = @JoinColumn(name = "schedule_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "study_method_id", referencedColumnName = "id"))
    private Set<StudyMethod> studyMethods = new HashSet<>();

    @Embedded
    private Recurrence recurrence;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private Set<Reminder> reminders = new HashSet<>();

    private String notes;
}
