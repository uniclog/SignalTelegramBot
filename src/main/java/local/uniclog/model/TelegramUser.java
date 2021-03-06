package local.uniclog.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Builder
@ToString
@EqualsAndHashCode
public class TelegramUser {
    private Long id;
    private String userName;
    private Boolean subscriber;
}
