package ie.atu.notificationserviceapplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class NotificationControllerIntegrationTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(notificationController).build();
    }

    @Test
    public void testSendNotification() throws Exception {
        NotificationDTO notificationDTO = new NotificationDTO("1", "recipient@example.com", "Test message", "INFO");
        Notification notification = new Notification("1", "recipient@example.com", "Test message", "INFO", true);

        when(notificationService.sendNotification(any(Notification.class))).thenReturn(notification);

        mockMvc.perform(post("/notifications")
                        .contentType("application/json")
                        .content("{\"id\":\"1\",\"recipient\":\"recipient@example.com\",\"message\":\"Test message\",\"type\":\"INFO\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.recipient").value("recipient@example.com"))
                .andExpect(jsonPath("$.message").value("Test message"))
                .andExpect(jsonPath("$.type").value("INFO"));

        verify(notificationService, times(1)).sendNotification(any(Notification.class));
    }

    @Test
    public void testGetNotification() throws Exception {
        Notification notification = new Notification("1", "recipient@example.com", "Test message", "INFO", true);
        NotificationDTO notificationDTO = new NotificationDTO("1", "recipient@example.com", "Test message", "INFO");

        when(notificationService.getNotificationById("1")).thenReturn(notification);

        mockMvc.perform(get("/notifications/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.recipient").value("recipient@example.com"))
                .andExpect(jsonPath("$.message").value("Test message"))
                .andExpect(jsonPath("$.type").value("INFO"));

        verify(notificationService, times(1)).getNotificationById("1");
    }

    @Test
    public void testGetAllNotifications() throws Exception {
        Notification notification = new Notification("1", "recipient@example.com", "Test message", "INFO", true);
        NotificationDTO notificationDTO = new NotificationDTO("1", "recipient@example.com", "Test message", "INFO");

        when(notificationService.getAllNotifications()).thenReturn(List.of(notification));

        mockMvc.perform(get("/notifications"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].recipient").value("recipient@example.com"))
                .andExpect(jsonPath("$[0].message").value("Test message"))
                .andExpect(jsonPath("$[0].type").value("INFO"));

        verify(notificationService, times(1)).getAllNotifications();
    }

}