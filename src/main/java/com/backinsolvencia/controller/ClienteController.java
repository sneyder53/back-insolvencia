package com.backinsolvencia.controller;

import com.backinsolvencia.exception.ExceptionsInsolvencia;
import com.backinsolvencia.models.Cliente;
import com.backinsolvencia.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> findAllClientes() {
        List<Cliente> clientes = clienteService.findAllClientes();
        if (clientes == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findClienteById(@PathVariable long id) {
        Cliente cliente = clienteService.findClienteById(id);
        if (cliente == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<?> createCliente(@RequestBody Cliente cliente) {
        try{
            return ResponseEntity.ok(clienteService.addCliente(cliente));
        } catch (ExceptionsInsolvencia e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateCliente(@RequestBody Cliente cliente) {
        try {
            return ResponseEntity.ok(clienteService.updateCliente(cliente));
        } catch (ExceptionsInsolvencia e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable long id) {
        try {
            return ResponseEntity.ok(clienteService.deleteClienteById(id));
        } catch (ExceptionsInsolvencia e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
