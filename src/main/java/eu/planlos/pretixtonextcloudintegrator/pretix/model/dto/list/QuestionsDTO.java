package eu.planlos.pretixtonextcloudintegrator.pretix.model.dto.list;

import eu.planlos.pretixtonextcloudintegrator.pretix.model.dto.single.QuestionDTO;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record QuestionsDTO(
        @NotNull Integer count,
        String next,
        String previous,
        @NotNull List<QuestionDTO> results
) {}