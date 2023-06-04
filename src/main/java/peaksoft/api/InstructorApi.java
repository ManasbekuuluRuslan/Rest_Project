package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.InstructorRequest;
import peaksoft.dto.InstructorResponse;
import peaksoft.entity.Instructor;
import peaksoft.service.InstructorService;

import java.util.List;

@RestController
@RequestMapping("instructors")
@RequiredArgsConstructor
public class InstructorApi {
    private final InstructorService instructorService;

    @GetMapping
    public List<InstructorResponse> getAllInstructors(){
        return instructorService.getAllInstructors();
    }

    @PostMapping
    public InstructorResponse saveInstructor(@RequestBody InstructorRequest instructorRequest){
        return instructorService.saveInstructor(instructorRequest);
    }
    @GetMapping("/{id}")
    public InstructorResponse getInstructorById(@PathVariable Long id){
        return instructorService.getInstructorById(id);
    }
    @PutMapping("/{id}")
    public InstructorResponse updateInstructor(@PathVariable Long id,@RequestBody InstructorRequest instructor){
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
