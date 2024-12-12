package com.backinsolvencia.controller;

import com.backinsolvencia.exception.ExceptionsInsolvencia;
import com.backinsolvencia.models.Causa;
import com.backinsolvencia.service.CausaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/causa")
@RequiredArgsConstructor
public class CausaController {

    private final CausaService causaService;

    @GetMapping
    public ResponseEntity<List<Causa>> findAllCausa() {
        List<Causa> causas = causaService.findAllCausa();
        if (causas.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(causas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Causa> findCausaById(@PathVariable long id) {
        Causa causa = causaService.findCausaById(id);
        if (causa == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(causa, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addCausa(@RequestBody Causa causa) {
        try{
            return new ResponseEntity<>(causaService.addCausa(causa), HttpStatus.OK);
        }catch (ExceptionsInsolvencia e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateCausa(@RequestBody Causa causa) {
        try{
            return new ResponseEntity<>(causaService.updateCausa(causa), HttpStatus.OK);
        } catch (ExceptionsInsolvencia e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCausa(@PathVariable long id) {
        try{
            return new ResponseEntity<>(causaService.deleteCausaById(id), HttpStatus.OK);
        } catch (ExceptionsInsolvencia e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
