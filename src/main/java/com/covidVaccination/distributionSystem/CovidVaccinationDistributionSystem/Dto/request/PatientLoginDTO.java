package com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PatientLoginDTO {
    String email;
    String password;
}
