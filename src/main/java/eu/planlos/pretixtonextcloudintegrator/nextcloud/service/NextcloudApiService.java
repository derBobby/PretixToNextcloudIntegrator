package eu.planlos.pretixtonextcloudintegrator.nextcloud.service;

import eu.planlos.pretixtonextcloudintegrator.nextcloud.config.NextcloudApiConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
public abstract class NextcloudApiService {

    protected final NextcloudApiConfig nextcloudApiConfig;
    protected final WebClient webClient;

    public NextcloudApiService(NextcloudApiConfig nextcloudApiConfig, WebClient webClient) {
        this.nextcloudApiConfig = nextcloudApiConfig;
        this.webClient = webClient;
    }
}