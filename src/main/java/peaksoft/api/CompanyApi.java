package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Company;
import peaksoft.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("companies")
@RequiredArgsConstructor
public class CompanyApi {
    private final CompanyService companyService;

    @GetMapping
    public List<Company> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @PostMapping
    public Company saveCompany(@RequestBody Company company){
        return companyService.saveCompany(company);
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Long id){
        return companyService.getCompanyById(id);
    }
    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable Long id,@RequestBody Company company){
        return companyService.updateCompany(id, company);
    }
    @DeleteMapping("/{id}")
    public String deleteCompany(@PathVariable Long id ){
        return companyService.deleteCompany(id);
    }
}