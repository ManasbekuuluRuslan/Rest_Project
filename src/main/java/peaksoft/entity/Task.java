package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Setter
@Getter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(generator = "task_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "task_seq",name = "task_gen",allocationSize = 1)
    private Long id;
    private String taskName;
    private String taskText;
    private LocalDate deadLine;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
    private Lesson lesson;


    public Task(String taskName, String taskText, LocalDate deadLine) {
        this.taskName = taskName;
        this.taskText = taskText;
        this.deadLine = deadLine;
    }
}
