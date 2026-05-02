package com.estoque.repository;

import com.estoque.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    @Query("SELECT m FROM Movimentacao m WHERE m.data >= :inicio AND m.data <= :fim ORDER BY m.data DESC")
    List<Movimentacao> findByPeriodo(LocalDate inicio, LocalDate fim);

    List<Movimentacao> findByProdutoId(Long produtoId);
}
