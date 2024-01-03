package com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String name;
    String docDegree;

    @ManyToOne
    @JsonIgnore
    VaccinationCenter vaccinationCenter;
    int patientCount;

    @OneToMany
    List<Patient> patients;

}
