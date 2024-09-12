package com.example.evaluation.domain.evaluation;

import jakarta.persistence.*;
import lombok.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "evaluation_scores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "evaluation_id", nullable = false)
    private Evaluation evaluation;

    @Column(name = "criterion", nullable = false)
    private String criterion;

    @Column(name = "score", nullable = false)
    private Integer score;
}
