package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.StudentRequest;
import peaksoft.dto.StudentResponse;
import peaksoft.entity.Group;
import peaksoft.entity.Student;
import peaksoft.repository.GroupRepository;
import peaksoft.repository.StudentRepository;
import peaksoft.service.StudentService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    @Override
    public StudentResponse saveStudent(StudentRequest studentRequest) {
        Student student = new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setGender(studentRequest.getGender());
        student.setStudyFormat(studentRequest.getStudyFormat());
        student.setIsBlocked(false);
        studentRepository.save(student);
        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getPhoneNumber(),
                student.getEmail(),
                student.getStudyFormat(),
                student.getGender());
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        return studentRepository.getAllStudents();
    }
    @Override
    public StudentResponse getStudentById(Long id) {
        Student student = new Student();
        studentRepository.findStudentById(id)
                .orElseThrow(() -> new NullPointerException("Student with id " + id + " not found "));
        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getPhoneNumber(),
                student.getEmail(),
                student.getStudyFormat(),
                student.getGender());
    }

    @Override
    public StudentResponse updateStudent(Long id, StudentRequest studentRequest) {
        Student student = studentRepository.findStudentById(id)
                        .orElseThrow(() -> new NullPointerException("Student with id " + id + "  not found "));
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setEmail(studentRequest.getEmail());
        student.setGender(studentRequest.getGender());
        student.setStudyFormat(studentRequest.getStudyFormat());
        studentRepository.save(student);
        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getPhoneNumber(),
                student.getEmail(),
                student.getStudyFormat(),
                student.getGender());
    }

    @Override
    public String deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return "Student with: " + id + "successfully deleted!";
        }
        else throw new NullPointerException("Student with id: " + id + " is not found");
    }
    @Override
    public List<StudentResponse> getAllStudentByBlockerOrNotBlocked(boolean isBlocked) {
        return studentRepository.findStudentByIsBlocked(isBlocked);
    }
    @Override
    public SimpleResponse blockOrUnBlock(Long id, Boolean isBlocked) {
        try {
            Student student1 = studentRepository.findById(id)
                    .orElseThrow(() ->
                            new NullPointerException("Student with id " + id + " not found "));
            student1.setIsBlocked(isBlocked);
            studentRepository.save(student1);
            return new SimpleResponse("Blocked", "students with " + id + "  is blocked");
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }
    @Override
    public void assignStudentToGroup(Long studentId, Long groupId) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->
                new NoSuchElementException("Student with id:" + studentId + "is not found"));
        Group group = groupRepository.findById(groupId).orElseThrow(()->
                new NoSuchElementException("Group with id:" + groupId + "is not found"));
        group.getStudentList().add(student);
        groupRepository.save(group);
        student.setGroup(group);
        studentRepository.save(student);
    }
}
