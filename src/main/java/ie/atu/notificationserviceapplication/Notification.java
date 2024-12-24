package ie.atu.notificationserviceapplication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "notifications")
public class Notification {

    @Id
    private String id;         // MongoDB will generate the id automatically
    private String message;
    private String recipient;
    private String type;
    private boolean isSent;

    // Default constructor
    public Notification() {}

    // Constructor with parameters
    public Notification(String message, String recipient, String type, boolean isSent) {
        this.message = message;
        this.recipient = recipient;
        this.type = type;
        this.isSent = isSent;
    }

    // Getters and setters
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