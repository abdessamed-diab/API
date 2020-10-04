package business;

import javax.crypto.BadPaddingException;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * this class represent core business logic, load set of employees and send birthdays emails.
 * since we are following Hexagonal Architecture, in the business logic layer we can see only non cross-cutting concern.
 */
public class MailSender implements IEndUserRequest{
    private ICollectionEmployees collectionUsers;
    private IMailServerProps mailServerProps;

    /**
     * initializing constructor
     * @param collectionUsers {@link ICollectionEmployees} list of users.
     * @param mailServerProps {@link IMailServerProps} properties to connect to valid smtp session.
     */
    public MailSender(ICollectionEmployees collectionUsers, IMailServerProps mailServerProps) {
        this.collectionUsers = collectionUsers;
        this.mailServerProps = mailServerProps;
    }

    @Override
    public int  send() throws IllegalArgumentException {
        Session authenticatedSession = getAuthenticatedSession();
        authenticatedSession.setDebug(true);

        AtomicInteger sentMessages = new AtomicInteger(0);
        collectionUsers.findEmployeesDateBirthdayToday().stream()
                .map( user -> buildMessage(authenticatedSession, user.getEmail(), user.getFirstName() )  )
                .filter(message -> message != null)
                .forEach(message -> {
                    try {
                        Transport.send(message);
                        sentMessages.incrementAndGet();
                    } catch (MessagingException e) {e.printStackTrace();}
                });

        return sentMessages.get();
    }

    /**
     * authenticate to smtp server with credentials
     * @return @{@link Session} valid smpt session.
     */
    private Session getAuthenticatedSession() {
        Properties props = mailServerProps.getProps();
        StringBuilder strBuilder = new StringBuilder(props.getProperty("pass"));
        try {
            strBuilder.replace(0, strBuilder.length(), mailServerProps.decryptPassword());
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

    /**
     *
     * @param authenticatedSession we need to provide a valid smtp session to construct an email.
     * @param internetAddress @{@link String} containing receiver email address.
     * @param firstName email message contains firstName of email receiver.
     * @return @{@link MimeMessage}
     */
    private MimeMessage buildMessage(Session authenticatedSession, String internetAddress, String firstName) {
        MimeMessage message = new MimeMessage(authenticatedSession);
        try {
            message.setFrom(authenticatedSession.getProperty("from"));
            message.addRecipients(Message.RecipientType.TO, internetAddress);
            message.setSubject("Happy birthday!");
            message.setText("Happy birthday, dear "+firstName+"!");
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
