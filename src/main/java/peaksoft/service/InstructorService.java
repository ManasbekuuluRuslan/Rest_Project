package peaksoft.service;

import peaksoft.dto.InstructorRequest;
import peaksoft.dto.InstructorResponse;
import peaksoft.entity.Instructor;

import java.util.List;

public interface InstructorService {
    InstructorResponse saveInstructor(InstructorRequest instructorRequest);
    List<InstructorResponse> getAllInstructors();
    InstructorResponse getInstructorById(Long id);
    InstructorResponse updateGInstructor(Long id, InstructorRequest instructorRequest);
    String deleteInstructor(Long id);
    void assignInstructorToCompany(Long instructorId, Long companyId);
}
