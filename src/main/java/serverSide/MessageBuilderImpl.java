package serverSide;

import business.IMessageBuilder;
import business.models.Employee;
import business.models.Message;
import org.apache.commons.lang3.StringUtils;
import serverSide.security.DecryptPassword;

import javax.crypto.BadPaddingException;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public final class MessageBuilderImpl implements IMessageBuilder {
    private Properties props;
    private String serverPropsFileName;
    private Session authenticatedSession;
    private Set<Message> messages= new HashSet<>();

    public static MessageBuilderImpl getInstance (String serverPropsFileName) {
        return new MessageBuilderImpl(serverPropsFileName);
    }

    private MessageBuilderImpl(String serverPropsFileName) throws IllegalArgumentException{
        this.serverPropsFileName = StringUtils.isBlank(serverPropsFileName) ? "mail/smtp.props" : serverPropsFileName;
        initSmtpServer();
    }

    private void initSmtpServer() {
        loadProperties();
        authenticatedSession = getAuthenticatedSession();
        authenticatedSession.setDebug(true);
    }

    @Override
    public void addMessage(String internetAddress, String firstName, Message simpleTextMessage) {
        simpleTextMessage.addAnchorToTextContent();
        simpleTextMessage.replaceAnchorOfTextContent(firstName);
        simpleTextMessage.addAnchorToTextContent();
        simpleTextMessage.replaceAnchorOfTextContent("!");
        simpleTextMessage.setUser(new Employee("last_name", firstName, LocalDate.now(), internetAddress));
        messages.add(simpleTextMessage);
    }

    public Set<Message> getMessages() {
        return messages;
    }

    @Override
    public int transportSendMessages() {
        AtomicInteger sentMessages = new AtomicInteger(0);
        messages.stream()
                .filter(message -> message != null)
                .map(message -> messageToMimeMessage(message))
                .forEach(mimeMessage -> {
                    try {
                        Transport.send(mimeMessage);
                        sentMessages.incrementAndGet();
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                });

        return sentMessages.get();
    }

    /**
     * decrypt supplied smtp server password provided in smtp.props.
     * @return decrypted gmail account password
     * @throws BadPaddingException check if provided encrypted password is correct and valid.
     * @throws IOException security decrypt Exception.
     */
    private String decryptPassword() throws BadPaddingException, IOException, IllegalArgumentException {
        if(props == null) {
            loadProperties();
        }
        return DecryptPassword.decrypt(props.getProperty("pass"));
    }

    /**
     * authenticate to smtp server with credentials
     * @return {@link Session} valid smpt session.
     */
    private Session getAuthenticatedSession() throws IllegalArgumentException{
        StringBuilder strBuilder = new StringBuilder(props.getProperty("pass"));
        try {
            strBuilder.replace(0, strBuilder.length(), decryptPassword());
        } catch ( BadPaddingException |IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("check provided password: "+strBuilder.toString());
        }

        return Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(props.getProperty("from"), strBuilder.toString());
            }
        });
    }

    private MimeMessage messageToMimeMessage(Message message) {
        MimeMessage mimeMessage = new MimeMessage(authenticatedSession);
        try {
            mimeMessage.addRecipients(javax.mail.Message.RecipientType.TO, message.getUser().getEmail());
            mimeMessage.setFrom(authenticatedSession.getProperty("from"));
            mimeMessage.setSubject(message.getSubject());
            mimeMessage.setText(message.getTextContent().toString());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return mimeMessage;
    }

    /**
     * load smtp server properties. in this case Gmail account.
     * @return {@link Properties}
     */
    private void loadProperties() {
        props = new Properties();
        URL url = ClassLoader.getSystemResource(serverPropsFileName);
        try {
            File file = new File(url.toURI());
            props.load(new FileInputStream(file));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
