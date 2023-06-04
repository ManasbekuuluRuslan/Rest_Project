package peaksoft.service;

import peaksoft.entity.Instructor;

import java.util.List;

public interface InstructorService {
    Instructor saveInstructor(Instructor instructor);
    List<Instructor> getAllInstructors();
    Instructor getInstructorById(Long id);
    Instructor updateGInstructor(Long id,Instructor instructor);
    String deleteInstructor(Long id);
    void assignInstructorToCompany(Long instructorId, Long companyId);
}
