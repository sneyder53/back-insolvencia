package com.backinsolvencia.controller;

import com.backinsolvencia.exception.ExceptionsInsolvencia;
import com.backinsolvencia.models.Acreedor;
import com.backinsolvencia.service.AcreedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/acreedor")
@RequiredArgsConstructor
public class AcreedorController {

    public final AcreedorService acreedorService;

    @GetMapping
    public ResponseEntity<List<Acreedor>> getAllAcreedor() {
        List<Acreedor> acreedores = acreedorService.getAllAcreedor();
        if (acreedores == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(acreedores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Acreedor> getAcreedorById(@PathVariable long id) {
        Acreedor acreedor = acreedorService.getAcreedorById(id);
        if (acreedor == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(acreedor, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addAcreedor(@RequestBody Acreedor acreedor) {
        try {
            Acreedor acreedorNew = acreedorService.addAcreedor(acreedor);
            return new ResponseEntity<>(acreedorNew, HttpStatus.CREATED);
        }catch (ExceptionsInsolvencia e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateAcreedor(@RequestBody Acreedor acreedor) {
        try {
            Acreedor acreedorUpdate = acreedorService.updateAcreedor(acreedor);
            return new ResponseEntity<>(acreedorUpdate, HttpStatus.OK);
        } catch (ExceptionsInsolvencia e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAcreedor(@PathVariable long id) {
        try {
            acreedorService.deleteAcreedor(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (ExceptionsInsolvencia e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }
}
