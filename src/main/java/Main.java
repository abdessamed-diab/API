import business.ICollectionEmployees;
import business.IEndUserRequest;
import business.IMessageBuilder;
import business.MailSender;
import serverSide.CollectionEmployeesImpl;
import serverSide.SmtpMailServer;
import userSide.EndUserConsole;

/**
 * entry point of KATA program,
 * since we are following hexagonal-architecture, we need to make sure to instantiate our classes from right to left.
 * right represent the server-side, and left is user-side.
 */
public class Main {

    public static void main(String[] args) {
        ICollectionEmployees collectionUsers = new CollectionEmployeesImpl("");
        IMessageBuilder mailServerProps = SmtpMailServer.getInstance("");
        IEndUserRequest endUserRequest = new MailSender(collectionUsers, mailServerProps);

        EndUserConsole console = new EndUserConsole(endUserRequest);

        console.launchUserInterfaceInteraction();
    }

}
