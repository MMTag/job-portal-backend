package com.eazybytes.jobportal.company.controller;

import com.eazybytes.jobportal.aspects.LogAspect;
import com.eazybytes.jobportal.dto.CompanyDto;
import com.eazybytes.jobportal.company.service.ICompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final ICompanyService companyService;

    @GetMapping(version = "1.0")
//    @LogAspect
    public ResponseEntity<List<CompanyDto>> getAllCompanies(){
        var list = companyService.getAllCompanies();
        return ResponseEntity.ok().body(list);
    }
}
