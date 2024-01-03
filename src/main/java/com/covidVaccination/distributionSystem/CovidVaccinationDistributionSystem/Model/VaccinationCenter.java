package com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class VaccinationCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String name;
    String type;
    int covaxinCount;
    int covidshieldCount;
    int sputnikCount;
    int patientsCount;
    int doctorCount;
    String address;
    @OneToMany(mappedBy = "vaccinationCenter")
    List<Doctor> doctors;

    public VaccinationCenter(UUID id, String name, String type, int covaxinCount, int covidshieldCount, int sputnikCount, int patientsCount, int doctorCount, String address, List<Doctor> doctors) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.covaxinCount = covaxinCount;
        this.covidshieldCount = covidshieldCount;
        this.sputnikCount = sputnikCount;
        this.patientsCount = patientsCount;
        this.doctorCount = doctorCount;
        this.address = address;
        this.doctors = doctors;
    }

    public VaccinationCenter() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCovaxinCount() {
        return covaxinCount;
    }

    public void setCovaxinCount(int covaxinCount) {
        this.covaxinCount = covaxinCount;
    }

    public int getCovidshieldCount() {
        return covidshieldCount;
    }

    public void setCovidshieldCount(int covidshieldCount) {
        this.covidshieldCount = covidshieldCount;
    }

    public int getSputnikCount() {
        return sputnikCount;
    }

    public void setSputnikCount(int sputnikCount) {
        this.sputnikCount = sputnikCount;
    }

    public int getPatientsCount() {
        return patientsCount;
    }

    public void setPatientsCount(int patientsCount) {
        this.patientsCount = patientsCount;
    }

    public int getDoctorCount() {
        return doctorCount;
    }

    public void setDoctorCount(int doctorCount) {
        this.doctorCount = doctorCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
}
