package com.example.evaluation.controller;


import com.example.evaluation.domain.evaluation.Evaluation;
import com.example.evaluation.domain.evaluation.EvaluationRequestDTO;
import com.example.evaluation.domain.evaluation.EvaluationResponseDTO;

import com.example.evaluation.repository.EvaluationRepository;
import com.example.evaluation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluation")
@Transactional
public class EvaluationController {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @GetMapping("get")
    public List<EvaluationResponseDTO> getAll() {
        List<EvaluationResponseDTO> evaluationList = evaluationRepository.findAll().stream().map(EvaluationResponseDTO::new).toList();
        System.out.println("Metodo getAll() de EvaluationController executado.");
        return evaluationList;
    }

    @PostMapping("create")
    public void saveEvaluation(@RequestBody EvaluationRequestDTO data) {
        Evaluation userData = new Evaluation(data);
        evaluationRepository.save(userData);
    }


}
