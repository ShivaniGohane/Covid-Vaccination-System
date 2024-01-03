package com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Service;

import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Model.VaccinationCenter;
import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Repository.VaccinationCenterRepository;
import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.enums.VaccinationCenterPreference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VaccinationCenterService {
    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;

    public VaccinationCenter registerVaccinationCenter(VaccinationCenter vaccinationCenter){
        return vaccinationCenterRepository.save(vaccinationCenter);//save method return by JPA
    }

    public List<VaccinationCenter> getMinimumDoctorCountVC(){
        List<VaccinationCenter> vaccinationCenters = vaccinationCenterRepository.getMinimumDoctorCountVaccinationCenter();
        return vaccinationCenters;
    }

    public void updateDocCountByOne(VaccinationCenter vaccinationCenter){
        UUID id = vaccinationCenter.getId();
        int docCount = vaccinationCenter.getDoctorCount()+1;
        vaccinationCenterRepository.updateDocCountByOne(id, docCount);
    }

    public void updatePatientCountByOne(VaccinationCenter vaccinationCenter){
        UUID id = vaccinationCenter.getId();
        int patienCount = vaccinationCenter.getPatientsCount() + 1;
        vaccinationCenterRepository.updatePatientCountByOne(patienCount, id);
    }

    public List<VaccinationCenter> getMinimumVCOnTheBasisOfTypeAndPreference(String type, String preference){
        if(preference.equals("Sputnic")){
            return vaccinationCenterRepository.getAllvcOnBasisOfTypeAndSputnikCount(type);
        }
        else if(preference.equals("Covishield")){
            return  vaccinationCenterRepository.getAllvcOnBasisOfTypeAndCovidshieldCount(type);
        }
        else{
            return vaccinationCenterRepository.getAllvcOnBasisOfTypeAndCovaxinCount(type);
        }
    }
}
