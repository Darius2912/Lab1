package com.dariochaves.Laboratorio1.service;

import com.dariochaves.Laboratorio1.entity.Categoria;
import com.dariochaves.Laboratorio1.dto.CategoriaRequest;
import com.dariochaves.Laboratorio1.repository.CategoriaRepository;
import com.dariochaves.Laboratorio1.exception.RecursoNoEncontado;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listar() {
        return this.categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(()->
                        new RecursoNoEncontado("Categoría no encotrada:" + id) );
    }

    public Categoria crear(CategoriaRequest request) {
        Categoria categoria = new Categoria();

        categoria.setNombre(request.getNombre());
        categoria.setDescripcion(request.getDescripcion());

        return categoriaRepository.save(categoria);
    }

    public Categoria actualizar(Long id, CategoriaRequest request) {
        Categoria categoria = buscarPorId(id);

        categoria.setNombre(request.getNombre());
        return categoriaRepository.save(categoria);
    }

    public void eliminar(Long id) {
        Categoria categoria = buscarPorId(id);
        categoriaRepository.delete(categoria);
    }
}

