package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Company;
import peaksoft.repository.CompanyRepository;
import peaksoft.service.CompanyService;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    @Override
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }
    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).
                 orElseThrow(()-> new NullPointerException
                         ("Company with id: "+id+" is not found!"));
    }

    @Override
    public Company updateCompany(Long id, Company company) {
        Company company1 = companyRepository.findById(id).
                orElseThrow(()-> new NullPointerException
                        ("Company with id: "+id+" is not found!"));
        company1.setName(company.getName());
        company1.setAddress(company.getAddress());
        company1.setCountry(company.getCountry());
        company1.setPhoneNumber(company.getPhoneNumber());
        companyRepository.save(company);
        return company1;
    }

    @Override
    public String deleteCompany(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return "Company with id: "+id+ " successfully deleted!";
        }
        else throw new NullPointerException("Company with id: " + id + " is not found");
    }
}