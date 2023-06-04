package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import peaksoft.dto.CourseRequest;
import peaksoft.dto.CourseResponse;
import peaksoft.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("courses")
@RequiredArgsConstructor
public class CourseApi {
    private final CourseService courseService;

    @GetMapping
    public List<CourseResponse> getAllCourses(){
        return courseService.getAllCourses();
    }
    @PostMapping("/save/{id}/{instructorId}")
    public CourseResponse saveCourses(@RequestBody CourseRequest courseRequest,
                                      @PathVariable Long id,
                                      @PathVariable Long instructorId) {
        return courseService.saveCourse(courseRequest,id,instructorId);
    }
    @GetMapping("/{id}")
    public CourseResponse getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id);
    }
    @PutMapping("/{id}")
    public CourseResponse updateCourse(@PathVariable Long id,@RequestBody CourseRequest course){
        return courseService.updateCourse(id, course);
    }
    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable Long id ){
        return courseService.deleteCourse(id);
    }
}
