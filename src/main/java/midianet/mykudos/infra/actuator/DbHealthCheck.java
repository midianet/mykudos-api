package midianet.mykudos.infra.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.boot.actuate.health.Health;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.HealthIndicator;

import midianet.mykudos.infra.helper.MessageHelper;

@Component
@RequiredArgsConstructor
public class DbHealthCheck implements HealthIndicator {
    private final JdbcTemplate template;
    private final MessageHelper messageHelper;

    @Value("${db.health.check.sql}")
    private String checkSQL;

    @Override
    public Health health() {
        try {
            template.query(checkSQL, new SingleColumnRowMapper<>());
            return Health.up().build();
        } catch (Exception e) {
            return Health.down().withDetail(messageHelper.getMessage("app.message.error.database"), 500).build();
        }
    }

}
