package com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Exceptions;

public class WrongCredentialException extends RuntimeException{
    public WrongCredentialException(String msg){
        super(msg);
    }
}
