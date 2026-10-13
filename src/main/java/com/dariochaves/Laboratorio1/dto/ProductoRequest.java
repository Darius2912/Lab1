package com.dariochaves.Laboratorio1.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class ProductoRequest {

    @NotBlank
    private String nombre;

    private String descripcion;

    @NotNull
    @Positive
    private BigDecimal precio;

    @NotNull
    @PositiveOrZero
    private Integer cantidad;

    @NotNull
    private Long categoriaId;

    public ProductoRequest() {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }


}
