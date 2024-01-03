package com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Dto.responsed;

import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Model.Doctor;
import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Model.Patient;
import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Model.VaccinationCenter;
import lombok.*;

import javax.print.Doc;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AppointmentDTO {
    int doseNumber;
    Patient patient;
    UUID docId;
    String docName;
    UUID vcId;
    String vaccinationCenterName;
}
