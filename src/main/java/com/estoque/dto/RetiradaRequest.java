package com.estoque.dto;

import lombok.Data;

@Data
public class RetiradaRequest {
    private Long produtoId;
    private Integer quantidade;
    private String observacao;
}
