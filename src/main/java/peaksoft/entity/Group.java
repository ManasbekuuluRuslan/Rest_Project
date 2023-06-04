package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "groups")
@Setter
@Getter
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(generator = "group_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "group_seq",name = "group_gen",allocationSize = 1)
    private Long id;
    @Column(name = "group_name")
    private String groupName;
    private String imageLink;
    private String description;
    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH},mappedBy = "group")
    private List<Student> studentList;
    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH},mappedBy = "groupList")
    private List<Course> courseList;

    public Group(String groupName, String imageLink, String description) {
        this.groupName = groupName;
        this.imageLink = imageLink;
        this.description = description;
    }
}
