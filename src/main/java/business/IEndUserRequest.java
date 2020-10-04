package business;

/**
 * in order to interact with the business logic, we need to provide an interface for end user side.
 * end user side could be a front controller in the MVC design pattern, OR could represent simply a web page.
 * in this example, user side is a console.
 */
public interface IEndUserRequest {
    /**
     * send birthdays emails to all employees born today.
     * @return number of emails was sent.
     * @throws IllegalArgumentException since we are using gmail server as a default mail server in this example,
     * make sure that the encrypted password saved under resources/mail/smtp.props is correct and valid.
     */
    int send() throws IllegalArgumentException;
}
