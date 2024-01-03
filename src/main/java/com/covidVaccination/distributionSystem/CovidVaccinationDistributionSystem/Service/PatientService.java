package com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Service;

import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Dto.request.PatientLoginDTO;
import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Dto.request.PatientSignupDTO;
import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Dto.responsed.AppointmentDTO;
import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Exceptions.PatientDoesNotExistException;
import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Exceptions.WrongCredentialException;
import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Model.Doctor;
import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Model.Patient;
import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Model.VaccinationCenter;
import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Repository.PatientRepository;
import com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.enums.VaccinationCenterPreference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    VaccinationCenterService vaccinationCenterService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    MailService mailService;

    public Patient signUp(PatientSignupDTO patientSignupDTO){
        Patient patient = new Patient();
        patient.setName(patientSignupDTO.getName());
        patient.setGender(patientSignupDTO.getGender());
        patient.setEmail(patientSignupDTO.getEmail());
        patient.setAddress(patientSignupDTO.getAddress());
        patient.setAadharNumber(patientSignupDTO.getAadharNumber());
        patient.setPassword(patientSignupDTO.getPassword());
        patient.setPhoneNumber(patientSignupDTO.getPhoneNumber());
        patient.setVaccinationPreference(patientSignupDTO.getVaccinationPreference().toString());
        patientRepository.save(patient);
        return patient;
    }

    public Patient login(PatientLoginDTO patientLoginDTO){
        Patient patient = patientRepository.getPatientByEmail(patientLoginDTO.getEmail());
        if(patient==null){
            throw new PatientDoesNotExistException("PatientEmail Id is not registered in our portal.");
        }
        if(!patient.getPassword().equals(patientLoginDTO.getPassword())){
            throw new WrongCredentialException("Patient Entered Wrong Password");
        }
        return patient;
    }

    public AppointmentDTO createAppointment(String email, String vaccinationCenterPreference){
        //1.Patient by Email
        Patient p = patientRepository.getPatientByEmail(email);
        //2.Identify the patient vaccination preference
        String vPreference = p.getVaccinationPreference();
        List<VaccinationCenter> vcList = vaccinationCenterService.getMinimumVCOnTheBasisOfTypeAndPreference(vaccinationCenterPreference, vPreference);
        //3. Assigning Oth index vaccination center to patient
        VaccinationCenter patientVC = vcList.get(0);
        //4. Assign doctor who is handeling minimum number of patient to current patient
        List<Doctor> docList = doctorService.getMinimumDoctorOnTheBasisOfVC(patientVC.getId());
        //5. Take out minumum doctor
        Doctor patientDoctor = docList.get(0);

        //vaccinationCenter - > patient count + 1;
        //Doctor -> patientCount + 1;
        //Doctor - > List<Paient> add aptient - > databasr -> insert docId vs patient table ba;lane
        //return response body ->patient datails, patient vc details , doctor details

        updateDoseCountByOne(p);
        vaccinationCenterService.updateDocCountByOne(patientVC);
        doctorService.updatePatientCountByOne(patientDoctor);
        patientDoctor.getPatients().add(p);
        //doctorService.addPatientVsDoctor(p.getId(), patientDoctor.getId());
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setPatient(p);
        appointmentDTO.setDoseNumber(p.getDoseCount()+1);
        appointmentDTO.setDocId(patientDoctor.getId());
        appointmentDTO.setDocName(patientDoctor.getName());
        appointmentDTO.setVcId(patientVC.getId());
        appointmentDTO.setVaccinationCenterName(patientVC.getName());

        String to = p.getEmail();
        String sub = String.format("Congratulations !! %s your appointment got created", p.getName());
        String text = String.format("Hii %s," +
                        "\n Your appointment got created. Below are your appointment details :" +
                        "\n1. Dose Count : %d" +
                        "\n2. Doctor Name : %s" +
                        "\n3. Vaccination Center Name  : %s" +
                        "\n4. Vaccination Center Address : %s",
                p.getName(),
                p.getDoseCount(),
                patientDoctor.getName(),
                patientVC.getName(),
                patientVC.getAddress()
        );
        mailService.generateMail(to,sub,text);
        return appointmentDTO;
    }

    public void updateDoseCountByOne(Patient patient){
        UUID id = patient.getId();
        int doseCount = patient.getDoseCount() + 1;
        patientRepository.updateCountByOne(id, doseCount);
    }
}
