package com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //object banega id will automatically create.
    UUID id;
    String name;
    String gender;
    @Column(unique = true)
    String aadharNumber;
    int doseCount;
    String vaccinationPreference;
    String address;
    @Column(unique = true)
    long phoneNumber;
    @Column(unique = true)
    String email;
    String password;
}
