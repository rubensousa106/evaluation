package com.example.evaluation.domain.evaluation;

import java.time.LocalDate;


public record EvaluationRequestDTO(
        Long idDocenteNaoDocente,
        Long idAvaliador,
        LocalDate dataAvaliacao,
        int pontuacao1,
        int pontuacao2,
        int pontuacao3,
        int pontuacao4,
        int pontuacao5,
        String comentario,
        String statusAvaliacao
) {

}

