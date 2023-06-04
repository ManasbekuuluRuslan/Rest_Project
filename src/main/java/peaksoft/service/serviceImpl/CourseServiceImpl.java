package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.CourseRequest;
import peaksoft.dto.CourseResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.repository.CompanyRepository;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.InstructorRepository;
import peaksoft.service.CourseService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;
    private final InstructorRepository instructorRepository;

    @Override
    public CourseResponse saveCourse(CourseRequest courseRequest, Long companyId, Long courseId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new NoSuchElementException("Company not found"));
        Instructor instructor=instructorRepository.findInstructorById(courseId).orElseThrow(()->
                new NoSuchElementException("instructor not found"));;
        Course course = new Course();
        course.setCourseName(courseRequest.getCourseName());
        course.setDescription(courseRequest.getDescription());
        course.setDateOfStart(LocalDate.now());
        course.setCompany(company);
        course.setInstructor(instructor);
        courseRepository.save(course);
        company.getCourseList().add(course);
        instructor.getCourseList().add(course);
        return new CourseResponse(
                course.getId(),
                course.getCourseName(),
                course.getDescription());
    }


    @Override
    public List<CourseResponse> getAllCourses() {
        return courseRepository.getAllCourses();
    }

    @Override
    public CourseResponse getCourseById(Long id) {
        Course course = courseRepository.findCourseById(id).orElseThrow(()
                        -> new NullPointerException("Course with id " + id + "  not found "));
        return new CourseResponse(
                course.getId(),
                course.getCourseName(),
                course.getDescription());
    }

    @Override
    public CourseResponse updateCourse(Long id, CourseRequest courseRequest) {
        Course course = courseRepository.findCourseById(id).orElseThrow(()
                        -> new NullPointerException("Course with id " + id + " not found "));
        course.setCourseName(courseRequest.getCourseName());
        course.setDescription(courseRequest.getDescription());
        courseRepository.save(course);
        return new CourseResponse(
                course.getId(),
                course.getCourseName(),
                course.getDescription());
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