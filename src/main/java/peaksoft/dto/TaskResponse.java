package peaksoft.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponse {
    private Long id;
    private String taskName;
    private String taskText;
    public TaskResponse(Long id, String taskName, String taskText) {
        this.id = id;
        this.taskName = taskName;
        this.taskText = taskText;
    }
}