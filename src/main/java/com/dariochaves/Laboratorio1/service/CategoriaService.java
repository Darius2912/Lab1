package com.dariochaves.Laboratorio1.service;

import com.dariochaves.Laboratorio1.exception.ConflictoException;
import com.dariochaves.Laboratorio1.model.Categoria;
import com.dariochaves.Laboratorio1.dto.CategoriaRequest;
import com.dariochaves.Laboratorio1.repository.CategoriaRepository;
import com.dariochaves.Laboratorio1.exception.RecursoNoEncontado;
import com.dariochaves.Laboratorio1.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;

    public CategoriaService(CategoriaRepository categoriaRepository, ProductoRepository productoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
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
        categoria.setDescripcion(request.getDescripcion());
        return categoriaRepository.save(categoria);
    }

    public void eliminar(Long id) {
        Categoria categoria = buscarPorId(id);

        if (productoRepository.existsCategoriaId(id)) {
            throw new ConflictoException("No se puede eliminar la categoría porque tiene productos asociados");
        }
        categoriaRepository.delete(categoria);
    }
}

