package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.StudentRequest;
import peaksoft.dto.StudentResponse;


import java.util.List;

public interface StudentService {
    StudentResponse saveStudent(StudentRequest studentRequest);
    StudentResponse getStudentById(Long id);
    List<StudentResponse> getAllStudents();
    StudentResponse updateStudent(Long id,StudentRequest studentRequest);
    String deleteStudent(Long id);
    List<StudentResponse> getAllStudentByBlockerOrNotBlocked(boolean isBlocked);
    SimpleResponse blockOrUnBlock(Long id, Boolean isBlocked);
    void assignStudentToGroup(Long studentId, Long groupId);
}
