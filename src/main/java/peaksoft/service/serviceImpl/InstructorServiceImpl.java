package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.repository.CompanyRepository;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.InstructorRepository;
import peaksoft.service.InstructorService;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final CompanyRepository companyRepository;
    @Override
    public Instructor saveInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id).
                orElseThrow(() -> new NullPointerException
                        ("Instructor with id: " + id + " is not found!"));
    }

    @Override
    public Instructor updateGInstructor(Long id, Instructor instructor) {
        Instructor instructor1 = instructorRepository.findById(id).
                orElseThrow(() -> new NullPointerException
                        ("Instructor with id: " + id + " is not found!"));
       instructor1.setFirstName(instructor.getFirstName());
       instructor1.setLastName(instructor.getLastName());
       instructor1.setSpecialization(instructor.getSpecialization());
       instructor1.setPhoneNumber(instructor.getPhoneNumber());
        instructorRepository.save(instructor1);
        return instructor1;
    }

    @Override
    public String deleteInstructor(Long id) {
        if (instructorRepository.existsById(id)) {
            instructorRepository.deleteById(id);
            return "Instructor with: " + id + "successfully deleted!";
        }
        else throw new NullPointerException("Instructor with id: " + id + " is not found");
    }

    @Override
    public void assignInstructorToCompany(Long instructorId, Long companyId) {
        Instructor instructor = instructorRepository.findById(instructorId).
                orElseThrow(() -> new NullPointerException
                        ("Instructor with id: " + instructorId + " is not found!"));
        Company company = companyRepository.findById(companyId).
                orElseThrow(()->new NullPointerException("Company with id: " + companyId + " is not found!"));
        instructor.getCompanyList().add(company);
        company.getInstructorList().add(instructor);
    }
}
