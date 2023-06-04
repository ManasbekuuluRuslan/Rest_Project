package peaksoft.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CourseRequest {
    private String courseName;
    private String description;
    public CourseRequest(String courseName, String description) {
        this.courseName = courseName;
        this.description = description;
    }
}