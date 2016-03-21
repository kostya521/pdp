package com.epam.pdp.javamail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import java.io.*;
import java.util.Date;
import java.util.Properties;

public class EmailClient {

    private static Properties props;
    private static final String FILE_PATH = "app.properties";
    private static Logger LOG = LoggerFactory.getLogger(App.class);

    private static String USER_NAME;
    private static String PASSWORD;

    static {
        props = new Properties();
        try {
            FileInputStream fis = new FileInputStream(FILE_PATH);
            props.load(fis);

            USER_NAME = props.getProperty("mail.user");
            PASSWORD = props.getProperty("mail.password");
        } catch (IOException e) {
            LOG.error("File '{}' not found or incorrect", FILE_PATH);
        }
    }

    private Session session;

    public Message[] getMessages(String folderName) {
        Message[] messages = new Message[0];
        try {
            Store store = getSession().getStore("pop3");
            store.connect(USER_NAME, PASSWORD);

            Folder folder = store.getFolder(folderName);
            folder.open(Folder.READ_ONLY);

            int messageCount = folder.getMessageCount();

            LOG.debug("Folder '{}' contains {} message(s)", folderName, messageCount);
            messages = folder.getMessages();

            fetchMessages(messages);

            folder.close(false);
            store.close();
        } catch (NoSuchProviderException e) {
            LOG.error(e.getMessage(), e);
        } catch (MessagingException e) {
            LOG.error("Folder {} doesn't exist", folderName);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return messages;
    }

    private void checkMessages(Message[] messages) throws MessagingException, IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0, n = messages.length; i < n; i++) {
            Message message = messages[i];
            sb.append("---------------------------------").append('\n');
            sb.append("Email Number " + (i + 1)).append('\n');
            sb.append("Subject: " + message.getSubject()).append('\n');
            sb.append("From: " + message.getFrom()[0]).append('\n');
            sb.append("Text: " + message.getContent().toString()).append('\n');
        }

        System.out.println(sb.toString());
    }

    private void fetchMessages(Message[] messages) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < messages.length; i++) {
            Message message = messages[i];
            System.out.println("---------------------------------");
            writePart(message);
            String line = reader.readLine();
            if ("YES".equals(line)) {
                message.writeTo(System.out);
            } else if ("QUIT".equals(line)) {
                break;
            }
        }
    }

    public static void writePart(Part p) throws Exception {
        if (p instanceof Message) {
            //Call method writeEnvelope
            writeEnvelope((Message) p);
        }

        System.out.println("----------------------------");
        System.out.println("CONTENT-TYPE: " + p.getContentType());

        //check if the content is plain text
        if (p.isMimeType("text/plain")) {
            System.out.println("This is plain text");
            System.out.println("---------------------------");
            System.out.println((String) p.getContent());
        }
        //check if the content has attachment
        else if (p.isMimeType("multipart/*")) {
            System.out.println("This is a Multipart");
            System.out.println("---------------------------");
            Multipart mp = (Multipart) p.getContent();
            int count = mp.getCount();
            for (int i = 0; i < count; i++)
                writePart(mp.getBodyPart(i));
        }
        //check if the content is a nested message
        else if (p.isMimeType("message/rfc822")) {
            System.out.println("This is a Nested Message");
            System.out.println("---------------------------");
            writePart((Part) p.getContent());
        }
        //check if the content is an inline image
        else if (p.isMimeType("image/jpeg")) {
            System.out.println("--------> image/jpeg");
            Object o = p.getContent();

            InputStream x = (InputStream) o;
            // Construct the required byte array
            System.out.println("x.length = " + x.available());
            int i = 0;
            byte[] bArray = new byte[x.available()];
            while ((i = (int) ((InputStream) x).available()) > 0) {
                int result = (int) (((InputStream) x).read(bArray));
                if (result == -1) {
                    break;
                }
            }
            FileOutputStream f2 = new FileOutputStream("/tmp/image.jpg");
            f2.write(bArray);
        } else if (p.getContentType().contains("image/")) {
            System.out.println("content type" + p.getContentType());
            File f = new File("image" + new Date().getTime() + ".jpg");
            DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
            com.sun.mail.util.BASE64DecoderStream test = (com.sun.mail.util.BASE64DecoderStream) p.getContent();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = test.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        } else {
            Object o = p.getContent();
            if (o instanceof String) {
                System.out.println("This is a string");
                System.out.println("---------------------------");
                System.out.println((String) o);
            } else if (o instanceof InputStream) {
                System.out.println("This is just an input stream");
                System.out.println("---------------------------");
                InputStream is = (InputStream) o;
                is = (InputStream) o;
                int c;
                while ((c = is.read()) != -1) {
                    System.out.write(c);
                }
            } else {
                System.out.println("This is an unknown type");
                System.out.println("---------------------------");
                System.out.println(o.toString());
            }
        }
    }

    /*
    * This method would print FROM,TO and SUBJECT of the message
    */
    public static void writeEnvelope(Message m) throws Exception {
        System.out.println("This is the message envelope");
        System.out.println("---------------------------");
        Address[] a;

        // FROM
        if ((a = m.getFrom()) != null) {
            for (int j = 0; j < a.length; j++) {
                System.out.println("FROM: " + a[j].toString());
            }
        }

        // TO
        if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
            for (int j = 0; j < a.length; j++) {
                System.out.println("TO: " + a[j].toString());
            }
        }

        // SUBJECT
        if (m.getSubject() != null) {
            System.out.println("SUBJECT: " + m.getSubject());
        }
    }

    private Session getSession() {
        if (session == null) {
            session = Session.getInstance(props);
        }
        return session;
    }

}
