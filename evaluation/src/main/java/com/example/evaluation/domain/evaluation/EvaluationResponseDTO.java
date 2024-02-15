package com.example.evaluation.domain.evaluation;

import java.time.LocalDate;
import java.util.Date;

public record EvaluationResponseDTO(Long idAvaliacao, Long idDocenteNaoDocente, Long idAvaliador,
                                    LocalDate dataAvaliacao, int pontuacao1, int pontuacao2, int pontuacao3,
                                    int pontuacao4, int pontuacao5, String comentario, String statusAvaliacao) {

        public EvaluationResponseDTO(Evaluation evaluation) {
            this(evaluation.getIdAvaliacao(), evaluation.getIdDocenteNaoDocente(), evaluation.getIdAvaliador(), evaluation.getDataAvaliacao(), evaluation.getPontuacao1(), evaluation.getPontuacao2(), evaluation.getPontuacao3(), evaluation.getPontuacao4(), evaluation.getPontuacao5(), evaluation.getComentario(), evaluation.getStatusAvaliacao());
        }
}
