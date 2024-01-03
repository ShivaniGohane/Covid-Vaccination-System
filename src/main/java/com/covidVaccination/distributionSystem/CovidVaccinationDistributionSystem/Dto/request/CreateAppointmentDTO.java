package com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Dto.request;

import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.enums.VaccinationCenterPreference;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateAppointmentDTO {
    UUID id;
    VaccinationCenterPreference vaccinationCenterPreference;
}
