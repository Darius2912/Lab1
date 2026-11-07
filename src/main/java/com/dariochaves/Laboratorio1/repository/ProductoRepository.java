package com.dariochaves.Laboratorio1.repository;

import com.dariochaves.Laboratorio1.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository  extends JpaRepository<Producto,Long> {

    boolean existsCategoriaId(Long CategoriaId);
}
