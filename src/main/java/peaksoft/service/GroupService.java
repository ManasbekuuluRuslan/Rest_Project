package peaksoft.service;

import peaksoft.dto.GroupRequest;
import peaksoft.dto.GroupResponse;
import peaksoft.entity.Group;

import java.util.List;

public interface GroupService {
    GroupResponse saveGroup(Long courseId, GroupRequest groupRequest);
    List<GroupResponse> getAllGroups();
    GroupResponse getGroupById(Long id);
    GroupResponse updateGroup(Long id,GroupRequest groupRequest);
    String deleteGroup(Long id);
    int countStudentsByGroup(Long groupId);
    int countStudentsByInstructor(Long instructorId);
}
