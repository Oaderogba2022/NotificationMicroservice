package ie.atu.notificationserviceapplication;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    @RabbitListener(queues = "notificationQueue")
    public void receiveNotification(Notification notification) {
        System.out.println("Received notification: " + notification.getMessage());
    }
}