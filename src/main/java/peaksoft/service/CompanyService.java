package peaksoft.service;

import peaksoft.dto.CompanyRequest;
import peaksoft.dto.CompanyResponse;
import java.util.List;

public interface CompanyService {
    CompanyResponse saveCompany(CompanyRequest companyRequest);
    List<CompanyResponse> getAllCompanies();
    CompanyResponse getCompanyById(Long id);
    CompanyResponse updateCompany(Long id,CompanyRequest companyRequest);
    String deleteCompany(Long id);
}
