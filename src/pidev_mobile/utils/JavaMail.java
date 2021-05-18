/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.utils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 *
 * @author seifeddine
 */
public class JavaMail extends Thread {

    public String recipient, type;
    public static String nomform;

    @Override
    public void run() {
        try {
            super.run(); //To change body of generated methods, choose Tools | Templates.
            sendMail(recipient, type);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void sendMail(String recepient, String type) throws Exception {
        System.out.println("preparing to");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "rightjob.inc@gmail.com";
        String myAccountPwd = "powerdev";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, myAccountPwd);
            }
        });

        if (type.equals("EmailConfirmation")) {
            EmailConfirmation(session, myAccountEmail, recepient);
        }
        if (type.equals("ForgetPassword")) {
            ForgetPassword(session, myAccountEmail, recepient);
        }
        if (type.equals("EmailReclamation")) {
            EmailReclamation(session, myAccountEmail, recepient);
        }
        if (type.equals("EmailOffreEmploi")) {
            EmailOffreEmploi(session, myAccountEmail, recepient);
        }
        if (type.equals("EmailOffreStage")) {
            EmailOffreStage(session, myAccountEmail, recepient);
        }
        if (type.equals("EmailEvenement")) {
            EmailEvenement(session, myAccountEmail, recepient);
        }
        if (type.equals("EmailFormation")) {
            EmailFormation(session, myAccountEmail, recepient);
        }
        if (type.equals("EmailConfirmationParticipant")) {
            EmailConfirmationParticipant(session, myAccountEmail, recepient);
        }
        if (type.equals("EmailConfirmationParticipantEvent")) {
            EmailConfirmationParticipantEvent(session, myAccountEmail, recepient);
        }
        if (type.equals("EmailEditParticipantEvent")) {
            EmailEditParticipantEvent(session, myAccountEmail, recepient);
        }
        if (type.equals("EmailEditParticipant")) {
            EmailEditParticipant(session, myAccountEmail, recepient);
        }

    }

    private static void EmailConfirmation(Session session, String myAccountEmail, String recepient) throws Exception {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("RightJob Email Confirmation");
            MimeMultipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText
                    = "<center>"
                    + "   <img src=\"https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f\" alt=\"RightJob\" height=\"175\" width=\"400\" />"
                    + "   <br>"
                    + "   <h1>Bienvenu dans notre site RightJob </h1>"
                    + "   <p>Votre adhesion est effectuer avec succés , j'espère vous trouvez ce que vous chercher, nous vous souhaitons nos chèrs salutation</p>"
                    + "   <p>Pour Activer votre compte clicker sur ce lien: <a href=\"http://127.0.0.1:8000/Activation/" + recepient + "\">Activer</a></p>"
                    + "   <p>Pour toute autre information veuillez nous contacter sur notre email RightJob.inc@gmail.com</p>"
                    + "</center>";
            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);
            //messageBodyPart = new MimeBodyPart();
            //String path="C:/xampp/htdocs/PiDev_Java/src/pidev_java/assets/Logo complet (1).png";
//            String path="https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f&fbclid=IwAR2gV_UhBG8KriaZyMAXg3SgKGkj1xG6w3-PsW25O3wUgDUZYyu-wstZLTk";
//            File file = new File(path.trim());
//            DataSource fds = new FileDataSource(file);
//            messageBodyPart.setDataHandler(new DataHandler(fds));
//            messageBodyPart.setHeader("Content-ID", "<image>");
            //multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

            Transport.send(message);
            System.out.println("message sent");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private static void ForgetPassword(Session session, String myAccountEmail, String recepient) throws Exception {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Quelqu'un veut changer votre mot de passe");
            MimeMultipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText
                    = "<center>"
                    + "   <img src=\"https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f\" alt=\"RightJob\" height=\"175\" width=\"400\" />"
                    + "   <br>"
                    + "   <h1>Votre demande de changement de mot de passe à été effectué, </h1>"
                    + "   <p>Si ce profil vous appartient</p>"
                    + "   <p>Executer ce lien</p>"
                    + "   <p><a href=\"http://127.0.0.1:8000/ChangePassword/" + recepient + "\">Changer mot de pass</a></p>"
                    + "   <p>Pour toute autre information veuillez nous contacter sur notre email RightJob.inc@gmail.com</p>"
                    + "</center>";
            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);
            //messageBodyPart = new MimeBodyPart();
            //String path="C:/xampp/htdocs/PiDev_Java/src/pidev_java/assets/Logo complet (1).png";
//            String path="https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f&fbclid=IwAR2gV_UhBG8KriaZyMAXg3SgKGkj1xG6w3-PsW25O3wUgDUZYyu-wstZLTk";
//            File file = new File(path.trim());
//            DataSource fds = new FileDataSource(file);
//            messageBodyPart.setDataHandler(new DataHandler(fds));
//            messageBodyPart.setHeader("Content-ID", "<image>");
            //multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("message sent");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private static void EmailReclamation(Session session, String myAccountEmail, String recepient) throws Exception {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Nouvelle Reclamation détecté");
            MimeMultipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText
                    = "<center>"
                    + "   <img src=\"https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f&fbclid=IwAR2gV_UhBG8KriaZyMAXg3SgKGkj1xG6w3-PsW25O3wUgDUZYyu-wstZLTk\" alt=\"RightJob\" height=\"175\" width=\"400\" />"
                    + "   <br>"
                    + "   <h1>Nouvelle Reclamation détécté </h1>"
                    + "   <p>Une nouvelle reclamation est intercépter, veuillez la consulter à partir de votre profil d'admin de reclamation ! Merci !</p>"
                    + "</center>";
            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);
//            messageBodyPart = new MimeBodyPart();
//            String path="C:/xampp/htdocs/PiDev_Java/src/pidev_java/assets/Logo complet (1).png";
////            String path="https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f&fbclid=IwAR2gV_UhBG8KriaZyMAXg3SgKGkj1xG6w3-PsW25O3wUgDUZYyu-wstZLTk";
//            File file = new File(path.trim());
//            DataSource fds = new FileDataSource(file);
//            messageBodyPart.setDataHandler(new DataHandler(fds));
//            messageBodyPart.setHeader("Content-ID", "<image>");
//            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("message sent");
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private static void EmailConfirmationParticipant(Session session, String myAccountEmail, String recepient) throws Exception {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Confirmation Participation");
            MimeMultipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText
                    = "<center>"
                    + "   <img src=\"https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f&fbclid=IwAR2gV_UhBG8KriaZyMAXg3SgKGkj1xG6w3-PsW25O3wUgDUZYyu-wstZLTk\" alt=\"RightJob\" height=\"175\" width=\"400\" />"
                    + "   <br>"
                    + "   <h1>Vous avez participer à la fomation " + nomform + " ! Merci !</h1>"
                    + "</center>";
            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);
//            messageBodyPart = new MimeBodyPart();
//            String path="C:/xampp/htdocs/PiDev_Java/src/pidev_java/assets/Logo complet (1).png";
////            String path="https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f&fbclid=IwAR2gV_UhBG8KriaZyMAXg3SgKGkj1xG6w3-PsW25O3wUgDUZYyu-wstZLTk";
//            File file = new File(path.trim());
//            DataSource fds = new FileDataSource(file);
//            messageBodyPart.setDataHandler(new DataHandler(fds));
//            messageBodyPart.setHeader("Content-ID", "<image>");
//            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("message sent");
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private static void EmailConfirmationParticipantEvent(Session session, String myAccountEmail, String recepient) throws Exception {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Confirmation Participation");
            MimeMultipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText
                    = "<center>"
                    + "   <img src=\"https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f&fbclid=IwAR2gV_UhBG8KriaZyMAXg3SgKGkj1xG6w3-PsW25O3wUgDUZYyu-wstZLTk\" alt=\"RightJob\" height=\"175\" width=\"400\" />"
                    + "   <br>"
                    + "   <h1>Vous avez participer à l'evenement " + nomform + " ! Merci !</h1>"
                    + "</center>";
            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);
//            messageBodyPart = new MimeBodyPart();
//            String path="C:/xampp/htdocs/PiDev_Java/src/pidev_java/assets/Logo complet (1).png";
////            String path="https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f&fbclid=IwAR2gV_UhBG8KriaZyMAXg3SgKGkj1xG6w3-PsW25O3wUgDUZYyu-wstZLTk";
//            File file = new File(path.trim());
//            DataSource fds = new FileDataSource(file);
//            messageBodyPart.setDataHandler(new DataHandler(fds));
//            messageBodyPart.setHeader("Content-ID", "<image>");
//            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("message sent");
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private static void EmailEditParticipantEvent(Session session, String myAccountEmail, String recepient) throws Exception {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Modification Evenement");
            MimeMultipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText
                    = "<center>"
                    + "   <img src=\"https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f&fbclid=IwAR2gV_UhBG8KriaZyMAXg3SgKGkj1xG6w3-PsW25O3wUgDUZYyu-wstZLTk\" alt=\"RightJob\" height=\"175\" width=\"400\" />"
                    + "   <br>"
                    + "   <h1> l'evenement " + nomform + " a éte modifier ! Merci !</h1>"
                    + "</center>";
            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);
//            messageBodyPart = new MimeBodyPart();
//            String path="C:/xampp/htdocs/PiDev_Java/src/pidev_java/assets/Logo complet (1).png";
////            String path="https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f&fbclid=IwAR2gV_UhBG8KriaZyMAXg3SgKGkj1xG6w3-PsW25O3wUgDUZYyu-wstZLTk";
//            File file = new File(path.trim());
//            DataSource fds = new FileDataSource(file);
//            messageBodyPart.setDataHandler(new DataHandler(fds));
//            messageBodyPart.setHeader("Content-ID", "<image>");
//            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("message sent");
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private static void EmailEditParticipant(Session session, String myAccountEmail, String recepient) throws Exception {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Modification Formation");
            MimeMultipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText
                    = "<center>"
                    + "   <img src=\"https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f&fbclid=IwAR2gV_UhBG8KriaZyMAXg3SgKGkj1xG6w3-PsW25O3wUgDUZYyu-wstZLTk\" alt=\"RightJob\" height=\"175\" width=\"400\" />"
                    + "   <br>"
                    + "   <h1> la formation " + nomform + " a éte modifier ! Merci !</h1>"
                    + "</center>";
            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);
//            messageBodyPart = new MimeBodyPart();
//            String path="C:/xampp/htdocs/PiDev_Java/src/pidev_java/assets/Logo complet (1).png";
////            String path="https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f&fbclid=IwAR2gV_UhBG8KriaZyMAXg3SgKGkj1xG6w3-PsW25O3wUgDUZYyu-wstZLTk";
//            File file = new File(path.trim());
//            DataSource fds = new FileDataSource(file);
//            messageBodyPart.setDataHandler(new DataHandler(fds));
//            messageBodyPart.setHeader("Content-ID", "<image>");
//            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("message sent");
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private static void EmailOffreEmploi(Session session, String myAccountEmail, String recepient) throws Exception {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Nouvelle Offre d'Emploi détecté");
            MimeMultipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText
                    = "<center>"
                    + "   <img src=\"https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f&fbclid=IwAR2gV_UhBG8KriaZyMAXg3SgKGkj1xG6w3-PsW25O3wUgDUZYyu-wstZLTk\" alt=\"RightJob\" height=\"175\" width=\"400\" />"
                    + "   <br>"
                    + "   <h1>Nouvelle Offre d'Emploi détécté </h1>"
                    + "   <p>Une nouvelle Offre d'Emploi est intercépter, veuillez la consulter à partir de votre profil d'admin des emploi ! Merci !</p>"
                    + "</center>";
            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);
//            messageBodyPart = new MimeBodyPart();
//            String path = "C:/xampp/htdocs/PiDev_Java/src/pidev_java/assets/Logo complet (1).png";
////            String path="https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f&fbclid=IwAR0OU2CDadtpG0HKz67ipJkayVO6TB3Mw4yIuYA_edUCmseK05wUu0yhO5o";
//            File file = new File(path.trim());
//            DataSource fds = new FileDataSource(file);
//            messageBodyPart.setDataHandler(new DataHandler(fds));
//            messageBodyPart.setHeader("Content-ID", "<image>");
//            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("message sent");
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private static void EmailOffreStage(Session session, String myAccountEmail, String recepient) throws Exception {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Nouvelle Offre de Stage détecté");
            MimeMultipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText
                    = "<center>"
                    + "   <img src=\"https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f&fbclid=IwAR2gV_UhBG8KriaZyMAXg3SgKGkj1xG6w3-PsW25O3wUgDUZYyu-wstZLTk\" alt=\"RightJob\" height=\"175\" width=\"400\" />"
                    + "   <br>"
                    + "   <h1>Nouvelle Offre de Stage détécté </h1>"
                    + "   <p>Une nouvelle Offre d'Emploi est intercépter, veuillez la consulter à partir de votre profil d'admin des emploi ! Merci !</p>"
                    + "</center>";
            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);
//            messageBodyPart = new MimeBodyPart();
//            String path = "C:/xampp/htdocs/PiDev_Java/src/pidev_java/assets/Logo complet (1).png";
////            String path="https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f&fbclid=IwAR2gV_UhBG8KriaZyMAXg3SgKGkj1xG6w3-PsW25O3wUgDUZYyu-wstZLTk";
//            File file = new File(path.trim());
//            DataSource fds = new FileDataSource(file);
//            messageBodyPart.setDataHandler(new DataHandler(fds));
//            messageBodyPart.setHeader("Content-ID", "<image>");
//            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("message sent");
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private static void EmailEvenement(Session session, String myAccountEmail, String recepient) throws Exception {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Nouvelle Evénement détecté");
            MimeMultipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText
                    = "<center>"
                    + "   <img src=\"https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f&fbclid=IwAR2gV_UhBG8KriaZyMAXg3SgKGkj1xG6w3-PsW25O3wUgDUZYyu-wstZLTk\" alt=\"RightJob\" height=\"175\" width=\"400\" />"
                    + "   <br>"
                    + "   <h1>Nouvelle Evénement détécté </h1>"
                    + "   <p>Une nouvelle Evénement est détécté, veuillez la consulter à partir de votre profil d'admin des événements ! Merci !</p>"
                    + "</center>";
            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);
//            messageBodyPart = new MimeBodyPart();
//            String path = "C:/xampp/htdocs/PiDev_Java/src/pidev_java/assets/Logo complet (1).png";
////            String path="https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f&fbclid=IwAR2gV_UhBG8KriaZyMAXg3SgKGkj1xG6w3-PsW25O3wUgDUZYyu-wstZLTk";
//            File file = new File(path.trim());
//            DataSource fds = new FileDataSource(file);
//            messageBodyPart.setDataHandler(new DataHandler(fds));
//            messageBodyPart.setHeader("Content-ID", "<image>");
//            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("message sent");
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private static void EmailFormation(Session session, String myAccountEmail, String recepient) throws Exception {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Nouvelle Formation détecté");
            MimeMultipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText
                    = "<center>"
                    + "   <img src=\"https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f&fbclid=IwAR2gV_UhBG8KriaZyMAXg3SgKGkj1xG6w3-PsW25O3wUgDUZYyu-wstZLTk\" alt=\"RightJob\" height=\"175\" width=\"400\" />"
                    + "   <br>"
                    + "   <h1>Nouvelle Formation détécté </h1>"
                    + "   <p>Une nouvelle Offre d'Emploi est intercépter, veuillez la consulter à partir de votre profil d'admin des événements ! Merci !</p>"
                    + "</center>";
            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);
//            messageBodyPart = new MimeBodyPart();
//            String path = "C:/xampp/htdocs/PiDev_Java/src/pidev_java/assets/Logo complet (1).png";
////            String path="https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f&fbclid=IwAR2gV_UhBG8KriaZyMAXg3SgKGkj1xG6w3-PsW25O3wUgDUZYyu-wstZLTk";
//            File file = new File(path.trim());
//            DataSource fds = new FileDataSource(file);
//            messageBodyPart.setDataHandler(new DataHandler(fds));
//            messageBodyPart.setHeader("Content-ID", "<image>");
//            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("message sent");
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

}
