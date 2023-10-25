package eu.planlos.pretixtonextcloudintegrator.common.notification.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

@ConfigurationProperties(prefix = "signal.api")
//TODO necessary?
@Profile("!TEST")
public record SignalApiConfig(boolean active, String address, String user, String password, String phoneSender,
                              String phoneReceiver) {

    public boolean inactive() {
        return !active;
    }
}