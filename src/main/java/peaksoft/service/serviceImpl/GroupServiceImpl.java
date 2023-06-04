package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.GroupRepository;
import peaksoft.service.GroupService;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    @Override
    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }
    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Group getGroupById(Long id) {
        return groupRepository.findById(id).
                orElseThrow(() -> new NullPointerException
                        ("Group with id: " + id + " is not found!"));
    }

    @Override
    public Group updateGroup(Long id, Group group) {
        Group group1 = groupRepository.findById(id).
                orElseThrow(() -> new NullPointerException
                        ("Group with id: " + id + " is not found!"));
        group1.setGroupName(group.getGroupName());
        group1.setDescription(group.getDescription());
        group1.setImageLink(group.getImageLink());
        groupRepository.save(group1);
        return group1;
    }

    @Override
    public String deleteGroup(Long id) {
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
            return "Group with: " + id + "successfully deleted!";
        }
        else throw new NullPointerException("Group with id: " + id + " is not found");
    }

    @Override
    public Group createGroup(Group group, List<Long> courseIds) {
        List<Course> courses = courseRepository.findAllById(courseIds);
        group.setCourseList(courses);
        return groupRepository.save(group);
    }

    @Override
    public int countStudentsByGroup(Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new NullPointerException("Group not found with id: " + groupId));
        return group.getStudentList().size();
    }

    @Override
    public int countStudentsByInstructor(Long instructorId) {
        return groupRepository.countStudentsByInstructor(instructorId);
    }
}
