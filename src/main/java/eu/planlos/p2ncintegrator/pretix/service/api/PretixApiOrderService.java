package eu.planlos.p2ncintegrator.pretix.service.api;

import eu.planlos.p2ncintegrator.common.ApiException;
import eu.planlos.p2ncintegrator.pretix.config.PretixApiConfig;
import eu.planlos.p2ncintegrator.pretix.model.dto.list.OrdersDTO;
import eu.planlos.p2ncintegrator.pretix.model.dto.single.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class PretixApiOrderService extends PretixApiService {

    private static final String FETCH_MESSAGE = "Fetched order from Pretix: {}";

    public PretixApiOrderService(PretixApiConfig pretixApiConfig, @Qualifier("PretixWebClient") WebClient webClient) {
        super(pretixApiConfig, webClient);
    }

    /*
     * Query
     */
    public List<OrderDTO> fetchAllOrders(String event) {

        if(isAPIDisabled()) {
            return Collections.emptyList();
        }

        OrdersDTO dto = webClient
                .get()
                .uri(orderListUri(event))
                .retrieve()
                .bodyToMono(OrdersDTO.class)
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(3)))
                .doOnError(error -> log.error("Message fetching all orders from Pretix API: {}", error.getMessage()))
                .block();

        if (dto != null) {
            List<OrderDTO> orderDTOList = new ArrayList<>(dto.results());
            orderDTOList.forEach(order -> log.info(FETCH_MESSAGE, order));
            return orderDTOList;
        }

        throw new ApiException(ApiException.IS_NULL);
    }

    public OrderDTO fetchOrderFromPretix(String event, String code) {

        if(isAPIDisabled()) {
            return null;
        }

        OrderDTO orderDto = webClient
                .get()
                .uri(specificOrderUri(event, code))
                .retrieve()
                .bodyToMono(OrderDTO.class)
                .retryWhen(Retry.fixedDelay(0, Duration.ofSeconds(1)))
                .doOnError(error -> log.error("Message fetching order={} from Pretix API: {}", code, error.getMessage()))
                .block();
        if (orderDto != null) {
            log.info(FETCH_MESSAGE, orderDto);
            return orderDto;
        }

        throw new ApiException(ApiException.IS_NULL);
    }

    /*
     * Uri generators
     */
    private String specificOrderUri(String event, String orderCode) {
        return String.join(
                "",
                "api/v1/organizers/", pretixApiConfig.organizer(),
                "/events/", event,
                "/orders/", orderCode, "/");
    }

    private String orderListUri(String event) {
        return String.join(
                "",
                "api/v1/organizers/", pretixApiConfig.organizer(),
                "/events/", event,
                "/orders/");
    }

    public String getEventUrl(String event, String orderCode) {
        return String.join(
                "",
                pretixApiConfig.address(),
                "/control/event/", pretixApiConfig.organizer(),
                "/", event,
                "/orders/", orderCode, "/");
    }
}