package ie.atu.notificationserviceapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /*@PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        // Call the service to create the notification and return the full object (including id)
        return notificationService.createNotification(notification);
    }

    @GetMapping("/{id}")
    public Optional<Notification> getNotificationById(@PathVariable String id) {
        return notificationService.getNotificationById(id);
    }

    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @PutMapping("/{id}")
    public Notification updateNotification(@PathVariable String id, @RequestBody Notification notification) {
        return notificationService.updateNotification(id, notification);
    }*/

    @PostMapping
    public ResponseEntity<NotificationDTO> sendNotification(@RequestBody NotificationDTO notificationDTO) {
        Notification notification = NotificationMapper.toEntity(notificationDTO);
        Notification sentNotification = notificationService.sendNotification(notification);
        return new ResponseEntity<>(NotificationMapper.toDTO(sentNotification), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> getNotification(@PathVariable String id) {
        Notification notification = notificationService.getNotificationById(id);
        return new ResponseEntity<>(NotificationMapper.toDTO(notification), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<NotificationDTO>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        List<NotificationDTO> notificationDTOs = new ArrayList<>();
        for (Notification notification : notifications) {
            notificationDTOs.add(NotificationMapper.toDTO(notification));
        }
        return new ResponseEntity<>(notificationDTOs, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable String id) {
        notificationService.deleteNotification(id);
    }
}