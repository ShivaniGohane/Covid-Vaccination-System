package com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Controller;

import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Dto.request.PatientLoginDTO;
import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Dto.request.PatientSignupDTO;
import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Dto.responsed.AppointmentDTO;
import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Dto.responsed.GeneralMessageDTO;
import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Exceptions.PatientDoesNotExistException;
import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Exceptions.WrongCredentialException;
import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Model.Patient;
import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Service.PatientService;
import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.enums.VaccinationCenterPreference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    PatientService patientService;

    @PostMapping("/signup")
    public ResponseEntity SignUp(@RequestBody PatientSignupDTO patientSignupDTO){
        Patient patient = patientService.signUp(patientSignupDTO);
        return new ResponseEntity(patient, HttpStatus.CREATED);
        //we can control what our API going to return - responseEntity
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody PatientLoginDTO patientLoginDTO){
        try {
            Patient patient = patientService.login(patientLoginDTO);
            return new ResponseEntity(patient, HttpStatus.OK);
        }catch (WrongCredentialException wrongCredentialException){
            return new ResponseEntity(new GeneralMessageDTO(wrongCredentialException.getMessage()), HttpStatus.UNAUTHORIZED);//401
        }catch (PatientDoesNotExistException patientDoesNotExistException){
            return new ResponseEntity(new GeneralMessageDTO(patientDoesNotExistException.getMessage()), HttpStatus.NOT_FOUND); //404
        }
    }

    @GetMapping("/createappointment")
    public ResponseEntity createAppointment(@RequestParam("email") String email, @RequestParam("vaccinationCenterPreference") VaccinationCenterPreference vaccinationCenterPreference){
        AppointmentDTO appointmentDTO = patientService.createAppointment(email, vaccinationCenterPreference.toString());
        return new ResponseEntity(appointmentDTO, HttpStatus.OK);
    }
}
