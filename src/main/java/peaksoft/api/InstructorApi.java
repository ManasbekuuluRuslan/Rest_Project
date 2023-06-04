package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Instructor;
import peaksoft.service.InstructorService;

import java.util.List;

@RestController
@RequestMapping("instructors")
@RequiredArgsConstructor
public class InstructorApi {
    private final InstructorService instructorService;

    @GetMapping
    public List<Instructor> getAllInstructors(){
        return instructorService.getAllInstructors();
    }

    @PostMapping
    public Instructor saveInstructor(@RequestBody Instructor instructor){
        return instructorService.saveInstructor(instructor);
    }
    @GetMapping("/{id}")
    public Instructor getInstructorById(@PathVariable Long id){
        return instructorService.getInstructorById(id);
    }
    @PutMapping("/{id}")
    public Instructor updateInstructor(@PathVariable Long id,@RequestBody Instructor instructor){
        return instructorService.updateGInstructor(id, instructor);
    }
    @DeleteMapping("/{id}")
    public String deleteInstructor(@PathVariable Long id ){
        return instructorService.deleteInstructor(id);
    }
    @GetMapping("/assign/{instructorId}/{companyId}")
    public String assignInstructorToCompany(@PathVariable Long instructorId, Long companyId){
        instructorService.assignInstructorToCompany(instructorId, companyId);
        return "Instructor assigned to Company";
    }
}
