/**
 * Created by Xiaokai Wang.
 */
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.mail.*;
import javax.mail.internet.InternetAddress;

public class CheckingMails {
    static String e_text;
    static String e_address;
    public static void check(String host, String storeType, String user,
                             String password)
    {
        try {

            //create properties field
            Properties properties = new Properties();

            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            //create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3s");

            store.connect(host, user, password);

            //create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();
            System.out.println("messages.length---" + messages.length);


            for (int i = 0, n = messages.length; i < n; i++) {
                Message message = messages[i];


                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());

                e_address = ((InternetAddress)message.getFrom()[0]).getAddress();
                System.out.println("From: " + e_address);
                System.out.print("Text: ");
                writePart(message);



            }

            //close the store and folder objects
            emailFolder.close(false);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String host = "pop.gmail.com";// change accordingly
        String mailStoreType = "pop3";
        String username = "cs1631testmail@gmail.com";// change accordingly
        String password = "cs1631test";// change accordingly

        check(host, mailStoreType, username, password);

    }

    public static void writePart(Part p) throws Exception {
        if (p.isMimeType("text/plain")) {

            System.out.println((String) p.getContent());
        }
        //check if the content has attachment
        else if (p.isMimeType("multipart/*")) {

            Multipart mp = (Multipart) p.getContent();
            int count = mp.getCount();
            for (int i = 0; i < count; i++)
                writePart(mp.getBodyPart(i));
        }

    }



}