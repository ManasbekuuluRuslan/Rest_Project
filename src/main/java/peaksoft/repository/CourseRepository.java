package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.CourseResponse;
import peaksoft.entity.Course;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    @Query("select new peaksoft.dto.CourseResponse(c.id,c.courseName,c.description)from Course c order by c.dateOfStart")
    List<CourseResponse> getAllCourses();
    Optional<Course> findCourseById(Long id);
}
