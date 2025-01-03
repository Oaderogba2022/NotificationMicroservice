package ie.atu.notificationserviceapplication;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationMessageConsumer {

    @RabbitListener(queues = "notificationQueues")
    public void handleNotification(Notification notification) {
        System.out.println("Received Notification: " + notification);
    }
}