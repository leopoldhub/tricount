package fr.univlille.da2i.hubert.etu.tricount.utils;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class MailUtils {

    public static void sendMail(final JavaMailSender sender, final String from, final String to, final String subject, final String content) throws MessagingException {
        final MimeMessage message = sender.createMimeMessage();
        final MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content);
        sender.send(message);
    }

}
