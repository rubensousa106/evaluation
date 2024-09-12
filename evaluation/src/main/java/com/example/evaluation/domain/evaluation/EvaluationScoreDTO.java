package com.example.evaluation.domain.evaluation;


/*
 * Data Transfer Object for Evaluation Scores
 */
public record EvaluationScoreDTO(
        String criterion,
        int score
) {

}
