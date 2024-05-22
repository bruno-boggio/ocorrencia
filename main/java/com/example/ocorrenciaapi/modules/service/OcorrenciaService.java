package com.example.ocorrenciaapi.modules.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ocorrenciaapi.modules.repository.Ocorrencia;
import com.example.ocorrenciaapi.modules.repository.OcorrenciaRepository;

@Service
public class OcorrenciaService {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    public List<Ocorrencia> findAll() {
        return ocorrenciaRepository.findAll();
    }

    public Optional<Ocorrencia> findById(Long id) {
        return ocorrenciaRepository.findById(id);
    }

    public Ocorrencia save(Ocorrencia ocorrencia) {
        return ocorrenciaRepository.save(ocorrencia);
    }

    public void deleteById(Long id) {
        ocorrenciaRepository.deleteById(id);
    }
}

