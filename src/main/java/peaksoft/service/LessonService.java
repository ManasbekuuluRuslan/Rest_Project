package peaksoft.service;
import peaksoft.entity.Lesson;

import java.util.List;

public interface LessonService {
    Lesson saveLesson(Lesson lesson);
    Lesson createLesson(Lesson lesson, Long courseId);
    List<Lesson> getAllLessons();
    Lesson getLessonById(Long id);
    Lesson updateLesson(Long id,Lesson lesson);
    String deleteLesson(Long id);
}
