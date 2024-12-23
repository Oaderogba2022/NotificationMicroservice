package ie.atu.notificationserviceapplication;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public Notification getNotificationById(String notificationId) {
        Optional<Notification> notification = notificationRepository.findById(notificationId);
        return notification.orElse(null);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification updateNotification(String notificationId, Notification notificationDetails) {
        Notification notification = getNotificationById(notificationId);
        if (notification != null) {
            notification.setMessage(notificationDetails.getMessage());
            notification.setRecipient(notificationDetails.getRecipient());
            notification.setSentAt(notificationDetails.getSentAt());
            notification.setType(notificationDetails.getType());
            return notificationRepository.save(notification);
        }
        return null;
    }

    public boolean deleteNotification(String notificationId) {
        Notification notification = getNotificationById(notificationId);
        if (notification != null) {
            notificationRepository.delete(notification);
            return true;
        }
        return false;
    }
}
