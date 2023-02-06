package miu.edu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Avatar {
    @Id
    private String id;
    private String head;
    private String hair;
    private String eye;
    private String eyebrow;
    private String nose;
    private String mouth;
    private String ears;
    private String body;
    private String hat;
    private String top;
    private String topColour;
    private String hatColour;
    private String studentId;

}
