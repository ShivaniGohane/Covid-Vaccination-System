package com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Repository;

import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Model.Doctor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    //select * from doctor where vaccination_center_id=:vcid and patient_count = (select min(patient_count) from doctor where vaccination_center_id=:vcid
    @Query(value = "select * from doctor where vaccination_center_id=:vcid and patient_count = (select min(patient_count) from doctor where vaccination_center_id=:vcid)", nativeQuery = true)
    public List<Doctor> getMinimumDoctorOnTheBasisOfVC(UUID vcid);

    @Modifying
    @Transactional
    @Query(value = "update vaccination_center set doctor_count=:patientCount where id=:docId", nativeQuery = true)
    public void updatePatientCountByOne(UUID docId, int patientCount);

    @Modifying
    @Transactional
    @Query(value = "insert doctor_patients (doctor_id, patients_id) values (:docId, :patientId)", nativeQuery = true)
    public void insertIntoDoctorVSPatientsTable(UUID docId, UUID patientId);
}
