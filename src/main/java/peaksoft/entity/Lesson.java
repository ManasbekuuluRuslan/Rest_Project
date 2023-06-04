package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "lessons")
@Setter
@Getter
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(generator = "lesson_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "lesson_seq",name = "lesson_gen",allocationSize = 1)
    private Long id;
    @Column(name = "lesson_name")
    private String lessonName;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
    private Course course;
    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH},mappedBy = "lesson")
    private List<Task> taskList;

    public Lesson(String lessonName) {
        this.lessonName = lessonName;
    }
}
