package com.covidVaccination.distributionSystem.CovidVaccinationDistributionSystem.Service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;

@Service
public class MailService {
    @Autowired
    JavaMailSender javaMailSender;
    public void generateMail(String to, String sub, String text){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(sub);
            mimeMessageHelper.setText(text);
            javaMailSender.send(mimeMessage);
        }catch(Exception exception){
            System.out.println(exception);
        }
    }
}
