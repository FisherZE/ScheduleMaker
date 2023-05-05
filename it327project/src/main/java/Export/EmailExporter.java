package Export;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


import Converter.CSVConverter;
import Schedule.Schedule;
public class EmailExporter {
 
   public void sendEmail(Schedule schedule, String destination) throws IOException {
    String to = destination; // to address. It can be any like gmail, hotmail etc.
    final String from = "schedulemaker312@gmail.com"; // from address. As this is using Gmail SMTP.
    final String password = "ppfhiiwgirtvypwb"; // password for from mail address. 
    String host = "smtp.gmail.com";
    Properties prop = new Properties();
    prop.put("mail.smtp.host", host);
    prop.put("mail.smtp.port", "465");
    prop.put("mail.smtp.ssl.enable", "true");
    prop.put("mail.smtp.auth", "true");
   
    Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
     protected PasswordAuthentication getPasswordAuthentication() {
      return new PasswordAuthentication(from, password);
     }
    });
   
    try {
   
     Message message = new MimeMessage(session);
     message.setFrom(new InternetAddress(from));
     message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
     message.setSubject("Your Desired Schedule");
      
     String msg = "This email was automatically sent with your file as a .csv!";
      
     MimeBodyPart mimeBodyPart = new MimeBodyPart();
     mimeBodyPart.setContent(msg, "text/html");
       
     Multipart multipart = new MimeMultipart();
     multipart.addBodyPart(mimeBodyPart);
      
     MimeBodyPart attachmentBodyPart = new MimeBodyPart();
     CSVConverter converter = new CSVConverter();
     converter.outputCSV(schedule);
     String fileName = CSVConverter.getFileLocations().get(0);
     DataSource source = new FileDataSource(fileName);
     attachmentBodyPart.setDataHandler(new DataHandler(source));
     attachmentBodyPart.setFileName(fileName);
     multipart.addBodyPart(attachmentBodyPart);
     CSVConverter.getFileLocations().clear();
     message.setContent(multipart);
   
     Transport.send(message);
   
     System.out.println("Mail successfully sent..");
   
    } catch (MessagingException e) {
     e.printStackTrace();
    }
   }
  }

