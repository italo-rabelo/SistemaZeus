package br.comp.zeus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCodigoRecuperacao(String to, String codigo) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        System.out.println("Enviado para: "+to);
        msg.setSubject("Código de recuperação de senha - ZeusApp");
        msg.setText("Olá!\n\nSeu código de recuperação de senha é: " +codigo);
        mailSender.send(msg);
    }

}
