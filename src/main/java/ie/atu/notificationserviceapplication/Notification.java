package ie.atu.notificationserviceapplication;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "notifications")
public class Notification {

    @Id
    private String id;
    private String message;
    private String recipient;
    private String type;
    private boolean isSent;

    public Notification() {}

    public Notification(String id, String recipient, String message, String type, boolean isSent) {
        this.id = id;
        this.recipient = recipient;
        this.message = message;
        this.type = type;
        this.isSent = isSent;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }
}