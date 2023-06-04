package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Group;
import peaksoft.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("groups")
@RequiredArgsConstructor
public class GroupApi {
    private final GroupService groupService;

    @GetMapping
    public List<Group> getAllGroups(){
        return groupService.getAllGroups();
    }

    @PostMapping
    public Group saveGroup(@RequestBody Group group){
        return groupService.saveGroup(group);
    }
    @GetMapping("/{id}")
    public Group getGroupById(@PathVariable Long id){
        return groupService.getGroupById(id);
    }
    @PutMapping("/{id}")
    public Group updateGroup(@PathVariable Long id,@RequestBody Group group){
        return groupService.updateGroup(id, group);
    }
    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable Long id ){
        return groupService.deleteGroup(id);
    }
    @PostMapping("/save")
    public ResponseEntity<Group> createGroup(@RequestBody Group group, @RequestParam("courseIds") List<Long> courseIds) {
        Group createdGroup = groupService.createGroup(group, courseIds);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGroup);
    }
    @GetMapping("/{groupId}/students/count")
    public ResponseEntity<Integer> countStudentsByGroup(@PathVariable Long groupId) {
        int studentCount = groupService.countStudentsByGroup(groupId);
        return new ResponseEntity<>(studentCount, HttpStatus.OK);
    }

    @GetMapping("/instructors/{instructorId}/students/count")
    public ResponseEntity<Integer> countStudentsByInstructor(@PathVariable Long instructorId) {
        int studentCount = groupService.countStudentsByInstructor(instructorId);
        return new ResponseEntity<>(studentCount, HttpStatus.OK);
    }
}