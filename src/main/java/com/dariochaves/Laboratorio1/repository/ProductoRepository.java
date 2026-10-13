package com.dariochaves.Laboratorio1.repository;

import com.dariochaves.Laboratorio1.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository  extends JpaRepository<Producto,Long> {

}
