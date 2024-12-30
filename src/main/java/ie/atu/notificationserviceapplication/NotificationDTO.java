package ie.atu.notificationserviceapplication;

public class NotificationDTO {
    private String id;
    private String recipient;
    private String message;
    private String type;

    public NotificationDTO() {}

    public NotificationDTO(String id, String recipient, String message, String type) {
        this.id = id;
        this.recipient = recipient;
        this.message = message;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
