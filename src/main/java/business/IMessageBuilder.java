package business;

import business.models.Message;

import java.util.Set;

public interface IMessageBuilder {

    /**
     * add message to a set of message that we want to send.
     * @param internetAddress {@link String} containing receiver email address.
     * @param firstName email message contains firstName of email receiver.
     */
    void addMessage(String internetAddress, String firstName, Message message);

    Set<Message> getMessages();

    int transportSendMessages();



}
