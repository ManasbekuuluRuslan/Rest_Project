package peaksoft.service;

import peaksoft.dto.TaskRequest;
import peaksoft.dto.TaskResponse;
import java.util.List;

public interface TaskService {
    TaskResponse saveTask(Long lessonId, TaskRequest taskRequest);
    List<TaskResponse> getAllTasks();
    TaskResponse getTaskById(Long id);
    TaskResponse updateTask(Long id,TaskRequest taskRequest);
    String deleteTask(Long id);
    void taskForLesson(Long taskId,Long lessonId);
}
