package eu.planlos.p2ncintegrator;

import eu.planlos.p2ncintegrator.common.notification.config.SignalApiConfig;
import eu.planlos.javapretixconnector.config.PretixApiConfig;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@TestConfiguration
public class TestConfig {

    @Bean
    public SignalApiConfig signalApiConfig() {
        return new SignalApiConfig(false, "testAddress", "testUser", "testPassword", "testSender", "testReceiver", 0, 0);
    }

    @Bean
    public PretixApiConfig pretixApiConfig() {
        return new PretixApiConfig(false, "testAddress", "testToken", "testOrganizer", new ArrayList<>(), 0, 0);
    }
}
