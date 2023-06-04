package peaksoft.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class TaskRequest {
    private String taskName;
    private String taskText;
}