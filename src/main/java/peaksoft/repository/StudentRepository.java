package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.StudentResponse;
import peaksoft.entity.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("select new peaksoft.dto.StudentResponse(s.id,s.firstName,s.lastName,s.phoneNumber,s.email,s.studyFormat,s.gender)from Student s")
    List<StudentResponse> getAllStudents();
    Optional<Student> findStudentById(Long id);
    List<StudentResponse> findStudentByIsBlocked(boolean isBlocked);
}
