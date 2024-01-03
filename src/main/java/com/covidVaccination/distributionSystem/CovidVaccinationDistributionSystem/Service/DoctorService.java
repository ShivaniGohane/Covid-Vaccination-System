package com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Service;

import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Model.Doctor;
import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Model.VaccinationCenter;
import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Repository.DoctorRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    VaccinationCenterService vaccinationCenterService;

    //Doctor will get assign in the vaccination center where have minimum no. of doctor
    public Doctor registerDoctor(@NotNull Doctor obj){
        List<VaccinationCenter> vaccinationCenterList = vaccinationCenterService.getMinimumDoctorCountVC();
        VaccinationCenter vaccinationCenter = vaccinationCenterList.get(0);
        obj.setVaccinationCenter(vaccinationCenter);
        vaccinationCenterService.updateDocCountByOne(vaccinationCenter);
        doctorRepository.save(obj);
        return obj;
    }

    public List<Doctor> getMinimumDoctorOnTheBasisOfVC(UUID vcid){
        return doctorRepository.getMinimumDoctorOnTheBasisOfVC(vcid);
    }

    public void updatePatientCountByOne(@org.jetbrains.annotations.NotNull Doctor doctor){
        UUID id = doctor.getId();
        int patientCount = doctor.getPatientCount() + 1;
        doctorRepository.updatePatientCountByOne(id, patientCount);
    }

    public void addPatientVsDoctor(UUID patientId, UUID doctorId){
        doctorRepository.insertIntoDoctorVSPatientsTable(doctorId, patientId);
    }
}
