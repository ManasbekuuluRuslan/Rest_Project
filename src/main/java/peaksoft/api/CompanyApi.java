package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.CompanyRequest;
import peaksoft.dto.CompanyResponse;
import peaksoft.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("companies")
@RequiredArgsConstructor
public class CompanyApi {
    private final CompanyService companyService;

    @GetMapping
    public List<CompanyResponse> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @PostMapping
    public CompanyResponse saveCompany(@RequestBody CompanyRequest company){
        return companyService.saveCompany(company);
    }

    @GetMapping("/{id}")
    public CompanyResponse getCompanyById(@PathVariable Long id){
        return companyService.getCompanyById(id);
    }
    @PutMapping("/{id}")
    public CompanyResponse updateCompany(@PathVariable Long id,@RequestBody CompanyRequest company){
        return companyService.updateCompany(id, company);
    }
    @DeleteMapping("/{id}")
    public String deleteCompany(@PathVariable Long id ){
        return companyService.deleteCompany(id);
    }
}