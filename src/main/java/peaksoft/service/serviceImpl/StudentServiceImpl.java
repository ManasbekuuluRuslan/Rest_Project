package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).
                orElseThrow(() -> new NullPointerException
                        ("Student with id: " + id + " is not found!"));
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student student1 = studentRepository.findById(id).
                orElseThrow(() -> new NullPointerException
                        ("Student with id: " + id + " is not found!"));
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setStudyFormat(student.getStudyFormat());
        studentRepository.save(student1);
        return student1;
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
    public void assignStudentToGroup(Long studentId, Long groupId) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->
                new NoSuchElementException("Student with id:" + studentId + "is not found"));
        Group group = groupRepository.findById(groupId).orElseThrow(()->
                new NoSuchElementException("Group with id:" + groupId + "is not found"));
        student.setGroup(group);
        group.getStudentList().add(student);

    }
}
