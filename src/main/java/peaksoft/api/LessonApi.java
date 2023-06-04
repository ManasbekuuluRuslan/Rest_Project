package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Lesson;
import peaksoft.service.LessonService;

import java.util.List;

@RestController
@RequestMapping("lessons")
@RequiredArgsConstructor
public class LessonApi {
    private final LessonService lessonService;

    @GetMapping
    public List<Lesson> getAllLessons(){
        return lessonService.getAllLessons();
    }
    @PostMapping("/{courseId}")
    public Lesson createLesson(@PathVariable Long courseId,
                                               @RequestBody Lesson lesson) {
        return lessonService.createLesson(lesson, courseId);
    }

    @PostMapping
    public Lesson saveLesson(@RequestBody Lesson lesson){
        return lessonService.saveLesson(lesson);
    }
    @GetMapping("/{id}")
    public Lesson getLessonById(@PathVariable Long id){
        return lessonService.getLessonById(id);
    }
    @PutMapping("/{id}")
    public Lesson updateLesson(@PathVariable Long id,@RequestBody Lesson lesson){
        return lessonService.updateLesson(id, lesson);
    }
    @DeleteMapping("/{id}")
    public String deleteLesson(@PathVariable Long id ){
        return lessonService.deleteLesson(id);
    }
}
