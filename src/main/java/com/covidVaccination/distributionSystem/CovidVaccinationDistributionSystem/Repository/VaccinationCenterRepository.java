package com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Repository;

import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Model.VaccinationCenter;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter, UUID> {
    //we have to register vaccination center into database

    @Query(value = "select * from vaccination_center where doctor_count = (select min(doctor_count) from covid_vaccination_distribution_system.vaccination_center)", nativeQuery = true)
    //query used by our code native query
    public List<VaccinationCenter> getMinimumDoctorCountVaccinationCenter();

    @Query(value = "select * from vaccination_center where type=:VCtype and covaxin_count!=0 and patients_count = (select min(patients_count) from vaccination_center)" , nativeQuery = true)
    public List<VaccinationCenter> getAllvcOnBasisOfTypeAndCovaxinCount(String VCtype);

    @Query(value = "select * from vaccination_center where type=:VCtype and covidshield_count!=0 and patients_count = (select min(patients_count) from vaccination_center)", nativeQuery = true)
    public List<VaccinationCenter> getAllvcOnBasisOfTypeAndCovidshieldCount(String VCtype);

    @Query(value = "select * from vaccination_center where type=:VCtype and sputnik_count!=0 and patients_count = (select min(patients_count) from vaccination_center)", nativeQuery = true)
    public List<VaccinationCenter> getAllvcOnBasisOfTypeAndSputnikCount(String VCtype);

    @Modifying
    @Transactional
    @Query(value = "update vaccination_center set patients_count=:patientCount where id=:id", nativeQuery = true)
    public void updatePatientCountByOne(int patientCount, UUID id);

    @Modifying
    @Transactional
    @Query(value = "update vaccination_center set doctor_count=:docCount where id=:id", nativeQuery = true)
    public void updateDocCountByOne(UUID id, int docCount);

}
