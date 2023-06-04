package peaksoft.service;
import peaksoft.entity.Course;

import java.util.List;

public interface CourseService {
    Course saveCourse(Course course);
    List<Course> getAllCourses();
    Course getCourseById(Long id);
    Course updateCourse(Long id,Course course);
    String deleteCourse(Long id);

}
