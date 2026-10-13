package com.dariochaves.Laboratorio1.controller;


import com.dariochaves.Laboratorio1.entity.Producto;
import com.dariochaves.Laboratorio1.dto.ProductoRequest;
import com.dariochaves.Laboratorio1.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public  ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> listar(@RequestParam long id){
        return productoService.listar();
    }

    @GetMapping("/{id}")
    public Producto buscarPorId(@PathVariable long id){
        return productoService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Producto> crear(@Valid @RequestBody ProductoRequest request){
        Producto producto = productoService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(producto);

    }
    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable long id, @Valid @RequestBody ProductoRequest request){
        return productoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
