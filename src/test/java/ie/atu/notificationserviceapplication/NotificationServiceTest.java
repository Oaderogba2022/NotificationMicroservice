package ie.atu.notificationserviceapplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private NotificationService notificationService;

    private Notification notification;

    @BeforeEach
    public void setUp() {
        notification = new Notification("1", "John Doe", "Test Message", "INFO", false);
    }

    @Test
    public void testGetAllNotifications() {
        when(notificationRepository.findAll()).thenReturn(Arrays.asList(notification));

        var notifications = notificationService.getAllNotifications();
        assertEquals(1, notifications.size());
        assertEquals("John Doe", notifications.get(0).getRecipient());
    }

    @Test
    public void testGetNotificationById() {
        when(notificationRepository.findById("1")).thenReturn(Optional.of(notification));

        var result = notificationService.getNotificationById("1");
        assertNotNull(result);
        assertEquals("Test Message", result.getMessage());
    }

    @Test
    public void testGetNotificationByIdNotFound() {
        when(notificationRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> notificationService.getNotificationById("1"));
    }

    @Test
    public void testSendNotification() {
        when(notificationRepository.save(any(Notification.class))).thenReturn(notification);

        Notification sentNotification = notificationService.sendNotification(notification);

        assertNotNull(sentNotification);
        assertTrue(sentNotification.isSent());

        verify(rabbitTemplate, times(1)).convertAndSend("notificationQueue", sentNotification);
    }

    @Test
    public void testDeleteNotification() {
        doNothing().when(notificationRepository).deleteById("1");

        notificationService.deleteNotification("1");

        verify(notificationRepository, times(1)).deleteById("1");
    }
}