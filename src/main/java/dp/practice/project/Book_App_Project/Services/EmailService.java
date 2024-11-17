//package dp.practice.project.Book_App_Project.Services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailService {
//
//    @Autowired
//    private JavaMailSender javaMailSender;
//    public void sendEmail(String to, String subject, String body){
//        SimpleMailMessage mailMessage=new SimpleMailMessage();
//        mailMessage.setTo(to);
//        mailMessage.setSubject(subject);
//        mailMessage.setText(body);
//        mailMessage.setFrom("dipanshu.p@outlook.com");
//        javaMailSender.send(mailMessage);
//
//    }
//}
