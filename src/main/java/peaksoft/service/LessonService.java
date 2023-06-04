package peaksoft.service;
import peaksoft.dto.LessonRequest;
import peaksoft.dto.LessonResponse;
import peaksoft.entity.Lesson;

import java.util.List;

public interface LessonService {
    LessonResponse saveLesson(LessonRequest lessonRequest,Long courseId);
    List<LessonResponse> getAllLessons();
    LessonResponse getLessonById(Long id);
    LessonResponse updateLesson(LessonRequest lessonRequest,Long id);
    String deleteLesson(Long id);
}
