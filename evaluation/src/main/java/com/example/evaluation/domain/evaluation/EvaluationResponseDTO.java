package com.example.evaluation.domain.evaluation;

import java.time.LocalDate;
import java.util.Date;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/*
REFORMULAR
 */

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record EvaluationResponseDTO(
        Long evaluationId,
        Long evaluatedId,
        Long evaluatorId,
        LocalDate evaluationDate,
        List<EvaluationScoreDTO> scores,
        String comment,
        String evaluationStatus
) {
    public EvaluationResponseDTO(Evaluation evaluation) {
        this(
                evaluation.getEvaluationId(),
                evaluation.getEvaluatedId(),
                evaluation.getEvaluatorId(),
                evaluation.getEvaluationDate(),
                evaluation.getScores().stream()
                        .map(score -> new EvaluationScoreDTO(score.getCriterion(), score.getScore()))
                        .collect(Collectors.toList()),
                evaluation.getComment(),
                evaluation.getEvaluationStatus().name()
        );
    }
}


