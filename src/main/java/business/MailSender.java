package business;

import business.models.Message;

/**
 * this class represent core business logic, load set of employees and send birthdays emails.
 * since we are following Hexagonal Architecture, in the business logic layer we can see only non cross-cutting concern.
 */
public class MailSender implements IEndUserRequest{
    private ICollectionEmployees collectionUsers;
    private IMessageBuilder messageBuilder;

    /**
     * initializing constructor
     * @param collectionUsers {@link ICollectionEmployees} list of users.
     * @param messageBuilder {@link IMessageBuilder} object responsible of managing messages.
     */
    public MailSender(ICollectionEmployees collectionUsers, IMessageBuilder messageBuilder) {
        this.collectionUsers = collectionUsers;
        this.messageBuilder = messageBuilder;
    }

    @Override
    public int  send() {
        collectionUsers.findEmployeesDateBirthdayToday().stream()
                .forEach( user -> messageBuilder.addMessage(user.getEmail(), user.getFirstName(),
                        new Message("Happy birthday!", "Happy birthday, dear "))
                );

        return messageBuilder.transportSendMessages();
    }




}
