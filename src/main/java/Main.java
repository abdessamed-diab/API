import business.ICollectionEmployees;
import business.IEndUserRequest;
import business.IMailServerProps;
import business.MailSender;
import serverSide.CollectionEmployeesImpl;
import serverSide.MailServerPropsImpl;
import userSide.EndUserConsole;

public class Main {

    public static void main(String[] args) {
        ICollectionEmployees collectionUsers = new CollectionEmployeesImpl("");
        IMailServerProps mailServerProps = MailServerPropsImpl.getInstance();
        IEndUserRequest endUserRequest = new MailSender(collectionUsers, mailServerProps);

        EndUserConsole console = new EndUserConsole(endUserRequest);

        console.launchUserInterfaceInteraction();
    }

}
