package eu.planlos.pretixtonextcloudintegrator.pretix.controller;

import eu.planlos.pretixtonextcloudintegrator.pretix.model.PretixQnaFilter;
import eu.planlos.pretixtonextcloudintegrator.pretix.service.PretixEventFilterService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PretixEventFilterController.URL_FILTER)
@Slf4j
public class PretixEventFilterController {

    public static final String URL_FILTER = "/api/v1/filter";

    private final PretixEventFilterService pretixEventFilterService;

    public PretixEventFilterController(PretixEventFilterService pretixEventFilterService) {
        this.pretixEventFilterService = pretixEventFilterService;
    }

    //TODO decouple PretixQnaFilter with DTO !!
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void webHook(@Valid @RequestBody PretixQnaFilter pretixQnaFilter, BindingResult bindingResult) {
        ControllerValidationErrorHandler.handle(bindingResult);

        log.info("Incoming filter={}", pretixQnaFilter);

        //TODO continue, update, delete, list
        pretixEventFilterService.addUserFilter(pretixQnaFilter);

        log.info("Filter saved with id={}", pretixQnaFilter.getId());
    }
}