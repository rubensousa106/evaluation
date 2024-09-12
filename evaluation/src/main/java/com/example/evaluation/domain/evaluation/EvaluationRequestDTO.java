package com.example.evaluation.domain.evaluation;

import java.time.LocalDate;
import java.util.List;

/*

 */
public record EvaluationRequestDTO(
        Long teacherOrStaffId,
        Long evaluatorId,
        LocalDate evaluationDate,
        List<EvaluationScoreDTO> scores,
        String comment,
        String evaluationStatus
) {

}



