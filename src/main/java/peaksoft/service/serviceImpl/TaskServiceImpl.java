package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }
    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).
                orElseThrow(() -> new NullPointerException
                        ("Task with id: " + id + " is not found!"));
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Task task1 = taskRepository.findById(id).
                orElseThrow(() -> new NullPointerException
                        ("Task with id: " + id + " is not found!"));
       task1.setTaskName(task.getTaskName());
       task1.setTaskText(task.getTaskText());
       task1.setDeadLine(task.getDeadLine());
        taskRepository.save(task1);
        return task1;
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
