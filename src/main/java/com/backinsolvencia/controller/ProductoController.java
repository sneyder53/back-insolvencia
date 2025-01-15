package com.backinsolvencia.controller;

import com.backinsolvencia.exception.ExceptionsInsolvencia;
import com.backinsolvencia.models.Producto;
import com.backinsolvencia.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> findAllProductos() {
        List<Producto> productos = productoService.findAllProductos();
        if (productos.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> findProductoById(@PathVariable long id) {
        Producto producto = productoService.findProductoById(id);
        if (producto == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @GetMapping("/acreedor/{id}")
    public ResponseEntity<List<Producto>> findProductoByAcreedor(@PathVariable long id) {
        List<Producto> productos = productoService.findProductoByAcreedor(id);
        if (productos.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createProducto(@RequestBody Producto producto) {
        try {
            return new ResponseEntity<>(productoService.addProducto(producto), HttpStatus.CREATED);
        }catch (ExceptionsInsolvencia e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateProducto(@RequestBody Producto producto) {
        try {
            return new ResponseEntity<>(productoService.updateProducto(producto), HttpStatus.OK);
        } catch (ExceptionsInsolvencia e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable long id) {
        try {
            productoService.deleteProducto(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ExceptionsInsolvencia e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
