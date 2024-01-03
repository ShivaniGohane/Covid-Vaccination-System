package com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Controller;

import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Model.Doctor;
import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Repository.DoctorRepository;
import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/register")
    public String registerDoctor(@RequestBody Doctor obj){
        doctorService.registerDoctor(obj);
        return "Doctor got registered";
    }
}
