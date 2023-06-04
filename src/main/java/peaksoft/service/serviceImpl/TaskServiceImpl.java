package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.TaskRequest;
import peaksoft.dto.TaskResponse;
import peaksoft.entity.Lesson;
import peaksoft.entity.Task;
import peaksoft.repository.LessonRepository;
import peaksoft.repository.TaskRepository;
import peaksoft.service.TaskService;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;
    @Override
    public TaskResponse saveTask(Long lessonId, TaskRequest taskRequest) {
        Lesson lesson = lessonRepository.findLessonById(lessonId).orElseThrow(()
                        -> new NullPointerException("Lesson with id " + lessonId + "  is not found "));
        Task task = new Task();
        task.setTaskName(taskRequest.getTaskName());
        task.setTaskText(taskRequest.getTaskText());
        taskRepository.save(task);
        lesson.getTaskList().add(task);
        lessonRepository.save(lesson);
        return new TaskResponse(
                task.getId(),
                task.getTaskName(),
                task.getTaskText());
    }
    @Override
    public List<TaskResponse> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        Task task = new Task();
        taskRepository.findTaskById(id).orElseThrow(() ->
                new NullPointerException("Task with id " + id + "  is not found "));
        return new TaskResponse(
                task.getId(),
                task.getTaskName(),
                task.getTaskText());
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {
        Task task = taskRepository.findTaskById(id).orElseThrow(() ->
                new NullPointerException("Task with id " + id + "  is not found "));
        task.setTaskName(taskRequest.getTaskName());
        task.setTaskText(taskRequest.getTaskText());
        taskRepository.save(task);
        return new TaskResponse(
                task.getId(),
                task.getTaskName(),
                task.getTaskText());
    }

    @Override
    public String deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return "Task with: " + id + "successfully deleted!";
        }
        else throw new NullPointerException("Task with id: " + id + " is not found");
    }

    @Override
    public void taskForLesson(Long taskId, Long lessonId) {
        Task task = taskRepository.findById(taskId).orElseThrow(()->
                new NullPointerException("Task with id: " + taskId + " is not found"));
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(()->
                        new NullPointerException("Lesson with id: " + lessonId + " is not found"));
        task.setLesson(lesson);
        lesson.getTaskList().add(task);
    }
}
