package com.backinsolvencia.controller;

import com.backinsolvencia.exception.ExceptionsInsolvencia;
import com.backinsolvencia.models.Insolvencia;
import com.backinsolvencia.service.InsolvenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insolvencia")
@RequiredArgsConstructor
public class InsolvenciaController {

    private final InsolvenciaService insolvenciaService;

    @GetMapping
    public ResponseEntity<List<Insolvencia>> findAllInsolvencia() {
        List<Insolvencia> insolvencias = insolvenciaService.findAllInsolvencia();
        if (insolvencias == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(insolvencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Insolvencia> findInsolvenciaById(@PathVariable long id) {
        Insolvencia insolvencia = insolvenciaService.findInsolvenciaById(id);
        if (insolvencia == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(insolvencia);
    }

    @PostMapping
    public ResponseEntity<?> createInsolvencia(@RequestBody Insolvencia insolvencia) {
        try {
            return ResponseEntity.ok(insolvenciaService.addInsolvencia(insolvencia));
        } catch (ExceptionsInsolvencia e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateInsolvencia(@RequestBody Insolvencia insolvencia) {
        try {
            return ResponseEntity.ok(insolvenciaService.updateInsolvencia(insolvencia));
        } catch (ExceptionsInsolvencia e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInsolvencia(@PathVariable long id) {
        try {
            return ResponseEntity.ok(insolvenciaService.deleteInsolvenciaById(id));
        } catch (ExceptionsInsolvencia e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/estado")
    public ResponseEntity<?> cambiarEstadoInsolvencia(@RequestBody Insolvencia insolvencia) {
        try {
            return ResponseEntity.ok(insolvenciaService.cambiarEstadoInsolvencia(insolvencia));
        } catch (ExceptionsInsolvencia e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: " + e.getMessage());
        }
    }


}
