package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.StudentRequest;
import peaksoft.dto.StudentResponse;
import peaksoft.entity.Student;
import peaksoft.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("students")
@RequiredArgsConstructor
public class StudentApi {
    private final StudentService studentService;

    @GetMapping
    public List<StudentResponse> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping
    public StudentResponse saveStudent(@RequestBody StudentRequest student){
        return studentService.saveStudent(student);
    }
    @GetMapping("/{id}")
    public StudentResponse getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }
    @PutMapping("/{id}")
    public StudentResponse updateStudent(@PathVariable Long id,@RequestBody StudentRequest student){
        return studentService.updateStudent(id, student);
    }
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id ){
        return studentService.deleteStudent(id);
    }
    @GetMapping("/block")
    public  List<StudentResponse>getAllStudentByBlockedOrUnBlocked(@RequestParam(required = false) boolean isBlocked){
        return studentService.getAllStudentByBlockerOrNotBlocked(isBlocked);

    }
    @PostMapping("/block/{id}")
    public SimpleResponse blockUnOrUnBlock(
            @PathVariable Long id,
            @RequestParam (required = false)
            boolean isBlocked){
        return studentService.blockOrUnBlock(id,isBlocked);
    }

    @GetMapping("/assign/{studentId}/{groupId}")
    public String assignStudentToGroup(@PathVariable Long studentId, Long groupId){
        studentService.assignStudentToGroup(studentId, groupId);
        return "Student assigned to Group";
    }
}
