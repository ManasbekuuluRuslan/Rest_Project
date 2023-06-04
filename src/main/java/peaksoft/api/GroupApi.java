package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.GroupRequest;
import peaksoft.dto.GroupResponse;
import peaksoft.entity.Group;
import peaksoft.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("groups")
@RequiredArgsConstructor
public class GroupApi {
    private final GroupService groupService;

    @GetMapping
    public List<GroupResponse> getAllGroups(){
        return groupService.getAllGroups();
    }
    @PostMapping("/save/{courseId}")
    public GroupResponse saveGroup(@PathVariable Long courseId,@RequestBody GroupRequest groupRequest) {
        return groupService.saveGroup(courseId,groupRequest);
    }
    @GetMapping("/{id}")
    public GroupResponse getGroupById(@PathVariable Long id){
        return groupService.getGroupById(id);
    }
    @PutMapping("/{id}")
    public GroupResponse updateGroup(@PathVariable Long id,@RequestBody GroupRequest group){
        return groupService.updateGroup(id, group);
    }
    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable Long id ){
        return groupService.deleteGroup(id);
    }

//    @GetMapping("/{groupId}/students/count")
//    public ResponseEntity<Integer> countStudentsByGroup(@PathVariable Long groupId) {
//        int studentCount = groupService.countStudentsByGroup(groupId);
//        return new ResponseEntity<>(studentCount, HttpStatus.OK);
//    }
//
//    @GetMapping("/instructors/{instructorId}/students/count")
//    public ResponseEntity<Integer> countStudentsByInstructor(@PathVariable Long instructorId) {
//        int studentCount = groupService.countStudentsByInstructor(instructorId);
//        return new ResponseEntity<>(studentCount, HttpStatus.OK);
//    }
}