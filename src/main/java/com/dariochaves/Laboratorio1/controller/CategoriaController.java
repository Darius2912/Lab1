package com.dariochaves.Laboratorio1.controller;

import com.dariochaves.Laboratorio1.entity.Categoria;
import com.dariochaves.Laboratorio1.dto.CategoriaRequest;
import com.dariochaves.Laboratorio1.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")

public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<Categoria> listar() {
        return categoriaService.listar();
    }


    @GetMapping("/{id}")
    public Categoria obtener(@PathVariable Long id) {
        return categoriaService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Categoria> crear(@Valid @RequestBody CategoriaRequest request) {
        Categoria categoria = categoriaService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }

    @PutMapping("/{id}")
    public Categoria actualizar(@PathVariable Long id, @Valid @RequestBody CategoriaRequest request) {
        Categoria categoria = categoriaService.actualizar(id, request);
        return categoriaService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
