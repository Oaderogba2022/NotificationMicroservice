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
        this.rabbitTemplate = rabbitTemplate;
        this.notificationRepository = notificationRepository;
    }


    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification getNotificationById(String id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
    }


    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public Notification updateNotification(String id, Notification notification) {
        notification.setId(id);
        return notificationRepository.save(notification);
    }

    public void deleteNotification(String id) {
        notificationRepository.deleteById(id);
    }

    public Notification sendNotification(Notification notification) {
        sendNotificationToQueue(notification);
        notification.setSent(true);
        return notificationRepository.save(notification);
    }

    // Private method to encapsulate RabbitMQ messaging logic
    private void sendNotificationToQueue(Notification notification) {
        rabbitTemplate.convertAndSend("notificationQueue", notification);
        System.out.println("Sent Notification to Queue: " + notification);
    }

}