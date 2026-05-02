package com.estoque.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "lotes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private BigDecimal precoCompra; // preço total do lote

    @Column(nullable = false)
    private LocalDate dataEntrada;

    private LocalDate dataValidade;

    @Column(nullable = false)
    private String fornecedor;

    @PrePersist
    public void prePersist() {
        if (dataEntrada == null) dataEntrada = LocalDate.now();
    }
}
