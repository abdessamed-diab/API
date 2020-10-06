package business.models;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.html.HTMLDocument;

import java.io.Serializable;

/**
 * this is thread safe message object.
 */
public class Message implements Serializable {
    public static final String anchorPoint= "$$$";
    private volatile String subject;
    private StringBuffer textContent;
    private HTMLDocument htmlContent;
    private Employee user;

    public Message() {
        subject = "";
        textContent= new StringBuffer("");
    }

    public Message(String subject, String textContent) throws IllegalArgumentException{
        if (StringUtils.isBlank(subject) || StringUtils.isBlank(textContent))
            throw new IllegalArgumentException();

        this.subject= subject;
        this.textContent= new StringBuffer(textContent);
    }

    // TODO you should test this.
    public Message(String subject, HTMLDocument htmlContent) throws IllegalArgumentException{
        if (StringUtils.isBlank(subject) || StringUtils.isBlank(textContent)
                || htmlContent == null || StringUtils.isBlank(htmlContent.getTextContent()) )
            throw new IllegalArgumentException();

        this.subject = subject;
        this.htmlContent= htmlContent;
    }

    public StringBuffer addAnchorToTextContent() {
        return textContent.append(anchorPoint);
    }

    public StringBuffer replaceAnchorOfTextContent(String value) {
        int startPosition = textContent.lastIndexOf(anchorPoint);
        return textContent.replace(startPosition, startPosition + anchorPoint.length(), value);
    }

    public String getSubject() {
        return subject;
    }

    public StringBuffer getTextContent() {
        return textContent;
    }

    public Employee getUser() {
        return user;
    }

    public void setUser(Employee user) {
        this.user = user;
    }
}
