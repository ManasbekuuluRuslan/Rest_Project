package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.InstructorRequest;
import peaksoft.dto.InstructorResponse;
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
    public InstructorResponse saveInstructor(InstructorRequest instructorRequest) {
        Instructor instructor = new Instructor();
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setSpecialization(instructorRequest.getSpecialization());
        instructorRepository.save(instructor);
        return new InstructorResponse(
                instructor.getId(),
                instructor.getFirstName(),
                instructor.getLastName(),
                instructor.getSpecialization());
    }
    @Override
    public List<InstructorResponse> getAllInstructors() {
        return instructorRepository.getAllInstructor();
    }

    @Override
    public InstructorResponse getInstructorById(Long id) {
        Instructor instructor = new Instructor();
        instructorRepository.findInstructorById(id).orElseThrow(()
                -> new NullPointerException("Instructor with id: " + id + " not found "));
        return new InstructorResponse(
                instructor.getId(),
                instructor.getFirstName(),
                instructor.getLastName(),
                instructor.getSpecialization());
    }



    @Override
    public InstructorResponse updateGInstructor(Long id, InstructorRequest instructorRequest) {
        Instructor instructor = instructorRepository.findInstructorById(id).orElseThrow(()
                        -> new NullPointerException("Instructor with id: " + id + " not found "));
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setSpecialization(instructorRequest.getSpecialization());
        instructorRepository.save(instructor);
        return new InstructorResponse(
                instructor.getId(),
                instructor.getFirstName(),
                instructor.getLastName(),
                instructor.getSpecialization());
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
