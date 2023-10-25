package eu.planlos.pretixtonextcloudintegrator.nextcloud.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

@ConfigurationProperties(prefix = "nextcloud.api")
//TODO necessary?
@Profile("!TEST")
public record NextcloudApiConfig(boolean active, String address, String user, String password, String defaultGroup) {

    public boolean inactive() {
        return !active;
    }
}