package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.LessonResponse;
import peaksoft.entity.Lesson;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {
    @Query("select new peaksoft.dto.LessonResponse(l.id,l.lessonName)from Lesson l")
    List<LessonResponse> getAllLessons();
    Optional<Lesson> findLessonById(Long id);

}
