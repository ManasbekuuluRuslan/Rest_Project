package peaksoft.service;

import peaksoft.entity.Company;
import java.util.List;

public interface CompanyService {
    Company saveCompany(Company company);
    List<Company> getAllCompanies();
    Company getCompanyById(Long id);
    Company updateCompany(Long id,Company company);
    String deleteCompany(Long id);
}
