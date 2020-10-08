package business.models;

import org.apache.commons.lang3.StringUtils;

/**
 * this is thread safe message object.
 */
public class TextMessage implements IMessage<String> {
    public static final String anchorPoint= "$$$";
    private volatile String subject;
    private StringBuffer textContent;
    private Employee user;

    public TextMessage(String subject, String textContent, Employee employee) throws IllegalArgumentException{
        if (StringUtils.isBlank(subject) || StringUtils.isBlank(textContent) || employee == null)
            throw new IllegalArgumentException();

        this.subject= subject;
        this.textContent= new StringBuffer(textContent);
        this.user = employee;
    }


    @Override
    public Employee to() {
        return user;
    }

    @Override
    public String subject() {
        return subject;
    }

    @Override
    public String content() {
        return textContent.toString();
    }
}
