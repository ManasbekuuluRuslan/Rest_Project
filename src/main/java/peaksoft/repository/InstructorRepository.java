package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Instructor;
@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Long> {

}
