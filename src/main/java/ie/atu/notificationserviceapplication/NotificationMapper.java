package ie.atu.notificationserviceapplication;

public class NotificationMapper {

    public static NotificationDTO toDTO(Notification notification)  {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setId(notification.getId());
        notificationDTO.setRecipient(notification.getRecipient());
        notificationDTO.setMessage(notification.getMessage());
        notificationDTO.setType(notification.getType());
        return notificationDTO;
    }

    public static Notification toEntity(NotificationDTO notificationDTO) {
        return new Notification(
                notificationDTO.getId(),
                notificationDTO.getRecipient(),
                notificationDTO.getMessage(),
                notificationDTO.getType(),
                false
        );
    }
}