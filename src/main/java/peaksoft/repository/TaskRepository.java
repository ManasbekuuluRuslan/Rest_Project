package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.TaskResponse;
import peaksoft.entity.Task;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    @Query("select new peaksoft.dto.TaskResponse(t.id,t.taskName,t.taskText)from Task t")
    List<TaskResponse> getAllTasks();
    Optional<Task> findTaskById(Long id);
}
