package com.t1.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idpedido;

    @Column(name = "total_items", nullable = false)
    private int totalItems;

    @Column(nullable = false)
    private double precio; // usamos double sencillo para el examen

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // Relación muchos a uno: cada pedido pertenece a un cliente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcliente", nullable = false)
    private Cliente cliente;

    // Constructores
    public Pedido() {}

    public Pedido(int totalItems, double precio) {
        this.totalItems = totalItems;
        this.precio = precio;
    }

    // Getters y setters
    public int getIdpedido() { return idpedido; }
    public void setIdpedido(int idpedido) { this.idpedido = idpedido; }

    public int getTotalItems() { return totalItems; }
    public void setTotalItems(int totalItems) { this.totalItems = totalItems; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}

