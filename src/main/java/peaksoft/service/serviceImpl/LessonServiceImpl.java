package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.LessonRequest;
import peaksoft.dto.LessonResponse;
import peaksoft.entity.Course;
import peaksoft.entity.Lesson;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.LessonRepository;
import peaksoft.service.LessonService;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    @Override
    public LessonResponse saveLesson(LessonRequest lessonRequest,Long courseId) {
        Course course = courseRepository.findCourseById(courseId).orElseThrow(()
                        -> new NullPointerException("Course with id: " + courseId + " not found "));
        Lesson lesson = new Lesson();
        lesson.setLessonName(lessonRequest.getLessonName());
        lessonRepository.save(lesson);
        course.getLessonList().add(lesson);
        lesson.setCourse(course);
        courseRepository.save(course);
        return new LessonResponse(lesson.getId(),
                lesson.getLessonName());
    }

    @Override
    public List<LessonResponse> getAllLessons() {
        return lessonRepository.getAllLessons();
    }

    @Override
    public LessonResponse getLessonById(Long id) {
        Lesson lesson = new Lesson();
        lessonRepository.findLessonById(id).orElseThrow(()
                -> new NullPointerException("Lesson with id: " + id + " not found"));
        return new LessonResponse(lesson.getId(),
                lesson.getLessonName());
    }
    @Override
    public LessonResponse updateLesson(LessonRequest lessonRequest,Long id) {
        Lesson lesson = lessonRepository.findLessonById(id).orElseThrow(()
                        -> new NullPointerException("Lesson with id " + id + " not found "));
        lesson.setLessonName(lessonRequest.getLessonName());
        lessonRepository.save(lesson);
        return new LessonResponse(
                lesson.getId(),
                lesson.getLessonName());
    }
    @Override
    public String deleteLesson(Long id) {
        if (lessonRepository.existsById(id)) {
            lessonRepository.deleteById(id);
            return "Lesson with: " + id + "successfully deleted!";
        }
        else throw new NullPointerException("Lesson with id: " + id + " not found");
    }
}