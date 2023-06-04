package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Task;
import peaksoft.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TaskApi {
    private final TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @PostMapping
    public Task saveTask(@RequestBody Task task){
        return taskService.saveTask(task);
    }
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id,@RequestBody Task task){
        return taskService.updateTask(id, task);
    }
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id ){
        return taskService.deleteTask(id);
    }
    @GetMapping("/for/{taskId}/{lessonId}")
    public String TaskForLesson(@PathVariable Long taskId, Long lessonId){
        taskService.taskForLesson(taskId, lessonId);
        return "Task assigned to Lesson";
    }
}
