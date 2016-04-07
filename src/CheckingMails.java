/**
 * Created by Xiaokai Wang.
 */
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.mail.*;
import javax.mail.internet.InternetAddress;

public class CheckingMails {
    static String e_text;
    static String e_address;
    static GetInfoToDB gdb = new GetInfoToDB();
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
                e_text = getText(message);
                Scanner in = new Scanner(e_text).useDelimiter("[^0-9]+");
                int integer = in.nextInt();
                System.out.print("Text: " + integer + "\n");
                gdb.store(e_address,integer);




            }
            gdb.getTrend();
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


    // extract the data we need from email body
    private static boolean textIsHtml = false;

    private static String getText(Part p) throws
            MessagingException, IOException {
        if (p.isMimeType("text/*")) {
            String s = (String)p.getContent();
            textIsHtml = p.isMimeType("text/html");
            return s;
        }

        if (p.isMimeType("multipart/alternative")) {
            // prefer html text over plain text
            Multipart mp = (Multipart)p.getContent();
            String text = null;
            for (int i = 0; i < mp.getCount(); i++) {
                Part bp = mp.getBodyPart(i);
                if (bp.isMimeType("text/plain")) {
                    if (text == null)
                        text = getText(bp);
                    continue;
                } else if (bp.isMimeType("text/html")) {
                    String s = getText(bp);
                    if (s != null)
                        return s;
                } else {
                    return getText(bp);
                }
            }
            return text;
        } else if (p.isMimeType("multipart/*")) {
            Multipart mp = (Multipart)p.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                String s = getText(mp.getBodyPart(i));
                if (s != null)
                    return s;
            }
        }

        return null;
    }



}