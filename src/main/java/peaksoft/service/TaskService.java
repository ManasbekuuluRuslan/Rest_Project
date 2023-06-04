package peaksoft.service;

import peaksoft.entity.Task;

import java.util.List;

public interface TaskService {
    Task saveTask(Task task);
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    Task updateTask(Long id,Task task);
    String deleteTask(Long id);
    void taskForLesson(Long taskId,Long lessonId);
}
