package business;

import business.models.IMessage;

import java.util.Set;

public interface IMessageBuilder {

    /**
     * add message to a set of message that we want to send.
     */
    void addMessage(IMessage genericMessage);

    Set<IMessage<? extends Object>> getGenericMessages();

    int transportSendMessages();



}
