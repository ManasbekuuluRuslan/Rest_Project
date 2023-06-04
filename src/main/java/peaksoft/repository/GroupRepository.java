package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.GroupResponse;
import peaksoft.entity.Group;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    @Query("select count (s) from Group g join Student s on s.group.id = g.id " +
            "join Instructor i on i.id = g.id where g.studentList = :instructorId")
    int countStudentsByInstructor(Long instructorId);

    @Query("select new peaksoft.dto.GroupResponse(g.id,g.groupName,g.imageLink,g.description)from Group g")
    List<GroupResponse> getAllGroup();
    Optional<Group> findGroupById(Long id);

}
