/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.helpers;

import java.net.PasswordAuthentication;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Marcos
 */
public class SendEmail {
    public void sendEmail(String emailTo, String code, String nome){
        new Thread(() -> {
            String emailAddressTo = emailTo;
            String emailFrom = "projetointerepi@gmail.com";
            String message = "Olá " + nome + ". Aqui está seu código de confirmação da entrega do EPI do dia " + new Date().toString() + "\nCódigo: " + code;
            String host = "smtp.gmail.com";
        
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");
        
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
                    return new javax.mail.PasswordAuthentication(emailFrom, "qsrbhokieysdjloi");
                }
            });
        
            try{
                MimeMessage messagem = new MimeMessage(session);
                messagem.setFrom(new InternetAddress(emailFrom));
                messagem.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddressTo));
                messagem.setSubject("Código de Autenticação do EPI");
                messagem.setText(message);
                Transport.send(messagem);
            } catch(Exception ex){
                System.out.println("Deu ruim demais zé rue");
                System.out.println(ex.toString());
            }
        }).start();
    }
}