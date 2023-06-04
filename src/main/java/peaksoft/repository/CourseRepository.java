package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Course;
@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

}
