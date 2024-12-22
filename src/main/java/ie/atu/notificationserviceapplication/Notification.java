package ie.atu.notificationserviceapplication;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "notifications")
public class Notification {

    @Id
    private String id;

    @NotEmpty
    private String message;

    private String recipient;

    private LocalDateTime sentAt;

    private String type;
}