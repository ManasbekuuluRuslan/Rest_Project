package peaksoft.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Gender;
import peaksoft.enums.StudyFormat;

@Getter
@Setter
@AllArgsConstructor
public class StudentRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;
    @Enumerated(EnumType.STRING)
    private Gender gender;
}