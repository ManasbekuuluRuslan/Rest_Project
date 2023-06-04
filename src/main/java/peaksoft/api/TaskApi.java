package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.TaskRequest;
import peaksoft.dto.TaskResponse;
import peaksoft.entity.Task;
import peaksoft.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TaskApi {
    private final TaskService taskService;

    @GetMapping
    public List<TaskResponse> getAllTasks(){
        return taskService.getAllTasks();
    }

    @PostMapping("/save/{lessonId}")
    public TaskResponse saveTasks(@PathVariable Long lessonId, @RequestBody TaskRequest taskRequest) {
        return taskService.saveTask(lessonId,taskRequest);
    }
    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }
    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Long id,@RequestBody TaskRequest task){
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
