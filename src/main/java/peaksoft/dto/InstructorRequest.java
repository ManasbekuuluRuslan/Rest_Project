package peaksoft.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class InstructorRequest {
    private String  firstName;
    private String  lastName;
    private String  specialization;
    public InstructorRequest(String firstName, String lastName, String specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
    }
}