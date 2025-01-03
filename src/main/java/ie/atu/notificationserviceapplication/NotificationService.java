package ie.atu.notificationserviceapplication;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, RabbitTemplate rabbitTemplate) {
        this.notificationRepository = notificationRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification getNotificationById(String id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
    }

    public Notification createNotification(Notification notification) {
        Notification savedNotification = notificationRepository.save(notification);
        // Publish the notification to the RabbitMQ queue
        rabbitTemplate.convertAndSend("notificationQueue", savedNotification);
        return savedNotification;
    }

    public Notification updateNotification(String id, Notification notification) {
        notification.setId(id);
        return notificationRepository.save(notification);
    }

    public void deleteNotification(String id) {
        notificationRepository.deleteById(id);
    }

    public Notification sendNotification(Notification notification) {
        notification.setSent(true);
        Notification savedNotification = notificationRepository.save(notification);
        rabbitTemplate.convertAndSend("notificationQueue", savedNotification);
        return savedNotification;
    }
}