package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.InstructorResponse;
import peaksoft.entity.Instructor;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Long> {
    Optional<Instructor> findInstructorById(Long id);
    @Query("select new peaksoft.dto.InstructorResponse(i.id, i.firstName, i.lastName,  i.specialization) from Instructor i")
    List<InstructorResponse> getAllInstructor();

}
