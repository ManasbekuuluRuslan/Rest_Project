package peaksoft.service;
import peaksoft.dto.CourseRequest;
import peaksoft.dto.CourseResponse;
import peaksoft.entity.Course;

import java.util.List;

public interface CourseService {
    CourseResponse saveCourse(CourseRequest courseRequest, Long companyLongId, Long courseId);

    List<CourseResponse> getAllCourses();
    CourseResponse getCourseById(Long id);
    CourseResponse updateCourse(Long id,CourseRequest courseRequest);
    String deleteCourse(Long id);

}
