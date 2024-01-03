package com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Dto.request;

import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.enums.VaccinationPreference;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PatientSignupDTO {
    String name;
    String email;
    String password;
    String aadharNumber;
    Long phoneNumber;
    String gender;
    VaccinationPreference vaccinationPreference;//sputnic, Covaxin, Covishield, xyz
    String address;
}
