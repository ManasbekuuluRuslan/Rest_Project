package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import peaksoft.dto.GroupRequest;
import peaksoft.dto.GroupResponse;
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
    public GroupResponse saveGroup(Long courseId, GroupRequest groupRequest) {
        Group group = new Group();
        group.setGroupName(groupRequest.getGroupName());
        group.setDescription(groupRequest.getDescription());
        group.setImageLink(groupRequest.getImageLink());
        groupRepository.save(group);
        return new GroupResponse(group.getId(),
                group.getGroupName(),
                group.getImageLink(),
                group.getDescription());
    }
    @Override
    public List<GroupResponse> getAllGroups() {
        return groupRepository.getAllGroup();
    }

    @Override
    public GroupResponse getGroupById(Long id) {
        Group group =
                groupRepository.findGroupById(id).orElseThrow(()
                        -> new NullPointerException("Group with id: " + id + " not found "));
        return new GroupResponse(
                group.getId(),
                group.getGroupName(),
                group.getImageLink(),
                group.getDescription());
    }

    @Override
    public GroupResponse updateGroup(Long id, GroupRequest groupRequest) {
        Group group = groupRepository.findGroupById(id).orElseThrow(()
                        -> new NullPointerException("Group with id: " + id + "  not found "));
        group.setGroupName(groupRequest.getGroupName());
        group.setDescription(groupRequest.getDescription());
        group.setImageLink(groupRequest.getImageLink());
        groupRepository.save(group);
        return new GroupResponse(group.getId(),
                group.getGroupName(),
                group.getImageLink(),
                group.getDescription());
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
