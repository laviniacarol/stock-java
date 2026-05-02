package com.estoque.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LoteRequest {
    private Long produtoId;
    private Integer quantidade;
    private BigDecimal precoCompra;
    private LocalDate dataValidade;
    private String fornecedor;
}
