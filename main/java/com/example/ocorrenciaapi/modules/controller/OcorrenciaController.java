package com.example.ocorrenciaapi.modules.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ocorrenciaapi.modules.repository.Ocorrencia;
import com.example.ocorrenciaapi.modules.service.OcorrenciaService;

@RestController
@RequestMapping("/api/ocorrencias")
public class OcorrenciaController {

    @Autowired
    private OcorrenciaService ocorrenciaService;

    @GetMapping
    public List<Ocorrencia> getAllOcorrencias() {
        return ocorrenciaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ocorrencia> getOcorrenciaById(@PathVariable Long id) {
        Optional<Ocorrencia> ocorrencia = ocorrenciaService.findById(id);
        return ocorrencia.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ocorrencia createOcorrencia(@RequestBody Ocorrencia ocorrencia) {
        return ocorrenciaService.save(ocorrencia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ocorrencia> updateOcorrencia(@PathVariable Long id, @RequestBody Ocorrencia ocorrenciaDetails) {
        Optional<Ocorrencia> ocorrencia = ocorrenciaService.findById(id);
        if (ocorrencia.isPresent()) {
            Ocorrencia existingOcorrencia = ocorrencia.get();
            existingOcorrencia.setDescricao(ocorrenciaDetails.getDescricao());
            existingOcorrencia.setStatus(ocorrenciaDetails.getStatus());
            Ocorrencia updatedOcorrencia = ocorrenciaService.save(existingOcorrencia);
            return ResponseEntity.ok(updatedOcorrencia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOcorrencia(@PathVariable Long id) {
        if (ocorrenciaService.findById(id).isPresent()) {
            ocorrenciaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

