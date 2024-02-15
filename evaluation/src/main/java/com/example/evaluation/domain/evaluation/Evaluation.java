package com.example.evaluation.domain.evaluation;

import com.example.evaluation.domain.user.UserRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Table(name = "evaluations")
@Entity(name = "evaluations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idAvaliacao")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAvaliacao; //Pode ser um número sequencial, um UUID ou outro identificador exclusivo.
    private Long idDocenteNaoDocente; //Identificação do docente ou não docente que está sendo avaliado.
    private Long idAvaliador; //Identificação do usuário que está realizando a avaliação.
    private LocalDate dataAvaliacao; //Data em que a avaliação foi realizada.
    private int pontuacao1;
    private int pontuacao2;
    private int pontuacao3;
    private int pontuacao4;
    private int pontuacao5;
    private String comentario;
    private String statusAvaliacao; //Pode indicar se a avaliação está pendente, concluída ou em andamento.



    public Evaluation(EvaluationRequestDTO data) {
        this.idDocenteNaoDocente = data.idDocenteNaoDocente();
        this.idAvaliador = data.idAvaliador();
        this.dataAvaliacao = data.dataAvaliacao();
        this.pontuacao1 = data.pontuacao1();
        this.pontuacao2 = data.pontuacao2();
        this.pontuacao3 = data.pontuacao3();
        this.pontuacao4 = data.pontuacao4();
        this.pontuacao5 = data.pontuacao5();
        this.comentario = data.comentario();
        this.statusAvaliacao = data.statusAvaliacao();
    }
}
