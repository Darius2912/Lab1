package com.dariochaves.Laboratorio1.service;


import com.dariochaves.Laboratorio1.entity.Categoria;
import com.dariochaves.Laboratorio1.entity.Producto;
import com.dariochaves.Laboratorio1.dto.ProductoRequest;
import com.dariochaves.Laboratorio1.repository.CategoriaRepository;
import com.dariochaves.Laboratorio1.repository.ProductoRepository;
import com.dariochaves.Laboratorio1.exception.RecursoNoEncontado;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id).orElseThrow(() -> new RecursoNoEncontado("Producto no encontrado:" + id
        ));
    }

    public Producto crear(ProductoRequest request) {
        Categoria categoria = categoriaRepository.findById(request.getCategoriaId()).orElseThrow(() ->
                new RecursoNoEncontado("Categoría no encontrada:" + request.getCategoriaId()));

        Producto producto = new Producto();

        producto.setCategoria(categoria);
        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setCantidad(request.getCantidad());

        return productoRepository.save(producto);
    }

    public Producto actualizar(Long id, ProductoRequest request) {
        Producto producto = buscarPorId(id);

        Categoria categoria = categoriaRepository.findById(request.getCategoriaId()).orElseThrow(() ->
                new RecursoNoEncontado("Categoría no encontrada:" + request.getCategoriaId()));

        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setCantidad(request.getCantidad());
        producto.setCategoria(categoria);

        return productoRepository.save(producto);

    }

    public void eliminar(Long id) {
        Producto producto = buscarPorId(id);
        productoRepository.delete(producto);
    }
}
