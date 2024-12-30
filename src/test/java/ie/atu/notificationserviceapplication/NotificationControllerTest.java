package ie.atu.notificationserviceapplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class NotificationControllerTest {

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
        Notification notification = new Notification("1", "John Doe", "Test Message", "INFO", true);
        when(notificationService.sendNotification(any(Notification.class))).thenReturn(notification);

        mockMvc.perform(post("/notifications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"1\", \"recipient\":\"John Doe\", \"message\":\"Test Message\", \"type\":\"INFO\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.recipient").value("John Doe"));
    }

    @Test
    public void testGetNotification() throws Exception {
        Notification notification = new Notification("1", "John Doe", "Test Message", "INFO", true);
        when(notificationService.getNotificationById("1")).thenReturn(notification);

        mockMvc.perform(get("/notifications/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.recipient").value("John Doe"));
    }

    @Test
    public void testGetAllNotifications() throws Exception {
        Notification notification = new Notification("1", "John Doe", "Test Message", "INFO", true);
        when(notificationService.getAllNotifications()).thenReturn(Arrays.asList(notification));

        mockMvc.perform(get("/notifications"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].recipient").value("John Doe"));
    }

}