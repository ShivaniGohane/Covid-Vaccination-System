package com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Exceptions;

public class PatientDoesNotExistException extends RuntimeException{
    public PatientDoesNotExistException(String msg){
        super(msg);
    }
}
