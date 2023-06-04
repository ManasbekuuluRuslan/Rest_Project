package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
    public Lesson saveLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson createLesson(Lesson lesson, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NullPointerException("Course not found with id: " + courseId));
        lesson.setCourse(course);
        return lessonRepository.save(lesson);
    }

    @Override
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id).
                orElseThrow(() -> new NullPointerException
                        ("Lesson with id: " + id + " is not found!"));
    }

    @Override
    public Lesson updateLesson(Long id, Lesson lesson) {
        Lesson lesson1 = lessonRepository.findById(id).
                orElseThrow(() -> new NullPointerException
                        ("Lesson with id: " + id + " is not found!"));
        lesson1.setLessonName(lesson.getLessonName());
        lessonRepository.save(lesson1);
        return lesson1;
    }

    @Override
    public String deleteLesson(Long id) {
        if (lessonRepository.existsById(id)) {
            lessonRepository.deleteById(id);
            return "Lesson with: " + id + "successfully deleted!";
        }
        else throw new NullPointerException("Lesson with id: " + id + " is not found");
    }
}