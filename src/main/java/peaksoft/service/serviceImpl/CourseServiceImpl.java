package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Course;
import peaksoft.repository.CourseRepository;
import peaksoft.service.CourseService;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).
                orElseThrow(() -> new NullPointerException
                        ("Course with id: " + id + " is not found!"));
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        Course course1 = courseRepository.findById(id).
                orElseThrow(() -> new NullPointerException
                        ("Course with id: " + id + " is not found!"));
        course1.setCourseName(course.getCourseName());
        course1.setDescription(course.getDescription());
        course1.setDateOfStart(course.getDateOfStart());
        courseRepository.save(course1);
        return course1;
    }

    @Override
    public String deleteCourse(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return "Course with: " + id + "successfully deleted!";
        }
        else throw new NullPointerException("Course with id: " + id + " is not found");
    }
}