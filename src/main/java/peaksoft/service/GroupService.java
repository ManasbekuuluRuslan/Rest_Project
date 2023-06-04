package peaksoft.service;

import peaksoft.entity.Group;

import java.util.List;

public interface GroupService {
    Group saveGroup(Group group);
    List<Group> getAllGroups();
    Group getGroupById(Long id);
    Group updateGroup(Long id,Group group);
    String deleteGroup(Long id);
    Group createGroup(Group group, List<Long> courseIds);
    int countStudentsByGroup(Long groupId);
    int countStudentsByInstructor(Long instructorId);
}
