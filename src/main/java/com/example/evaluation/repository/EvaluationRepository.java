package com.example.evaluation.repository;

import com.example.evaluation.domain.evaluation.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long>{

}
