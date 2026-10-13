package com.dariochaves.Laboratorio1.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table (name = "productos")

public class Producto {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String nombre;

    @Column (nullable = false)
    private String descripcion;

    @Column (nullable = false)
    private BigDecimal precio;

    @Column (nullable = false)
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "categoria_id",  nullable = false)
    private Categoria categoria;

    public Producto(String nombre, String descripcion, BigDecimal precio, Integer cantidad, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;

    }

    public Producto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
