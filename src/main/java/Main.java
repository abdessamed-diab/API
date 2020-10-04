import business.ICollectionUsers;
import business.IEndUserRequest;
import business.IMailServerProps;
import business.MailSender;
import serverSide.CollectionUsersImpl;
import serverSide.MailServerPropsImpl;
import userSide.EndUserConsole;

public class Main {

    public static void main(String[] args) {
        ICollectionUsers collectionUsers = new CollectionUsersImpl("");
        IMailServerProps mailServerProps = MailServerPropsImpl.getInstance();
        IEndUserRequest endUserRequest = new MailSender(collectionUsers, mailServerProps);

        EndUserConsole console = new EndUserConsole(endUserRequest);

        console.launchUserInterfaceInteraction();
    }

}
