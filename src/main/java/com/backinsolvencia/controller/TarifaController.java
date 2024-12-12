package com.backinsolvencia.controller;

import com.backinsolvencia.exception.ExceptionsInsolvencia;
import com.backinsolvencia.models.Tarifa;
import com.backinsolvencia.service.TarifaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarifa")
@RequiredArgsConstructor
public class TarifaController {

    private final TarifaService tarifaService;

    @GetMapping
    public ResponseEntity<List<Tarifa>> getAllTarifa(){
        List<Tarifa> tarifas = tarifaService.getAllTarifas();
        if(tarifas == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(tarifas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarifa> getTarifaById(@PathVariable Long id){
        Tarifa tarifa = tarifaService.getTarifaById(id);
        if(tarifa == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(tarifa);
    }

    @PostMapping
    public ResponseEntity<?> addTarifa(@RequestBody Tarifa tarifa) {
        try {
        Tarifa tarifanew = tarifaService.addTarifa(tarifa);
        if(tarifanew == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Los campos no pueden ser nulos o negativos " + tarifa.toString());
        }
            return ResponseEntity.status(HttpStatus.CREATED).body(tarifanew);
        }catch (ExceptionsInsolvencia e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }

    }

    @PutMapping
    public ResponseEntity<?> updateTarifa(@RequestBody Tarifa tarifa){
        try {
            Tarifa tarifaUpdate = tarifaService.updateTarifa(tarifa);
            if(tarifaUpdate == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Los campos no pueden ser nulos o negativos " + tarifa.toString());
            }
            return ResponseEntity.status(HttpStatus.OK).body(tarifaUpdate);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTarifa(@PathVariable Long id){
        try {
            String delete = tarifaService.deleteTarifa(id);
            return ResponseEntity.status(HttpStatus.OK).body(delete);
        }catch (ExceptionsInsolvencia e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage() );
        }
    }

}
