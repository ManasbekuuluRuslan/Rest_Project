package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.Gender;
import peaksoft.enums.StudyFormat;

@Entity
@Table(name = "students")
@Setter
@Getter
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(generator = "student_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "student_seq",name = "student_gen",allocationSize = 1)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;
    @Column(name = "study_format", columnDefinition = "VARCHAR(255)")
    private StudyFormat studyFormat;
    private Gender gender;
    private Boolean isBlocked;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private Group group;

    public Student(String firstName, String lastName, String phoneNumber, String email, StudyFormat studyFormat, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studyFormat = studyFormat;
        this.gender = gender;
    }
}
