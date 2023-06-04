package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import peaksoft.entity.Course;
import peaksoft.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("courses")
@RequiredArgsConstructor
public class CourseApi {
    private final CourseService courseService;

    @GetMapping
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @PostMapping
    public Course saveCourse(@RequestBody Course course){
        return courseService.saveCourse(course);
    }
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id);
    }
    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id,@RequestBody Course course){
        return courseService.updateCourse(id, course);
    }
    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable Long id ){
        return courseService.deleteCourse(id);
    }
}
