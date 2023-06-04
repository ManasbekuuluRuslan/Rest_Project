package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.CompanyRequest;
import peaksoft.dto.CompanyResponse;
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
    public CompanyResponse saveCompany(CompanyRequest companyRequest) {
        Company company = new Company();
        company.setName(companyRequest.getName());
        company.setAddress(companyRequest.getAddress());
        company.setPhoneNumber(companyRequest.getPhoneNumber());
        company.setCountry(companyRequest.getCountry());
        companyRepository.save(company);
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getCountry(),
                company.getAddress(),
                company.getPhoneNumber());
    }
    @Override
    public List<CompanyResponse> getAllCompanies() {
        return companyRepository.getAllCompanies();
    }

    @Override
    public CompanyResponse getCompanyById(Long id) {
        Company company = new Company();
        companyRepository.getCompanyById(id).orElseThrow(()
                -> new NullPointerException("Company with id " + id + "  not found "));
        return new CompanyResponse(company.getId(),
                company.getName(),
                company.getCountry(),
                company.getAddress(),
                company.getPhoneNumber());

    }

    @Override
    public CompanyResponse updateCompany(Long id, CompanyRequest companyRequest) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() ->
                        new NullPointerException("Company with id: " + id + " not found "));
        company.setName(companyRequest.getName());
        company.setCountry(companyRequest.getCountry());
        company.setAddress(companyRequest.getAddress());
        company.setPhoneNumber(companyRequest.getPhoneNumber());
        companyRepository.save(company);
        return new CompanyResponse(company.getId(),
                company.getName(),
                company.getCountry(),
                company.getAddress(),
                company.getPhoneNumber());
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