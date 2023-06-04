package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.LessonRequest;
import peaksoft.dto.LessonResponse;
import peaksoft.entity.Lesson;
import peaksoft.service.LessonService;

import java.util.List;

@RestController
@RequestMapping("lessons")
@RequiredArgsConstructor
public class LessonApi {
    private final LessonService lessonService;

    @GetMapping
    public List<LessonResponse> getAllLessons(){
        return lessonService.getAllLessons();
    }

    @PostMapping("/save/{courseId}")
    public LessonResponse saveLessons(@PathVariable Long courseId, @RequestBody LessonRequest lessonRequest) {
        return lessonService.saveLesson(lessonRequest,courseId);
    }
    @GetMapping("/{id}")
    public LessonResponse getLessonById(@PathVariable Long id){
        return lessonService.getLessonById(id);
    }
    @PutMapping("/{id}")
    public LessonResponse updateLesson(@PathVariable Long id,@RequestBody LessonRequest lesson){
        return lessonService.updateLesson(lesson,id);
    }
    @DeleteMapping("/{id}")
    public String deleteLesson(@PathVariable Long id ){
        return lessonService.deleteLesson(id);
    }
}
