package com.estoque.repository;

import com.estoque.model.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface LoteRepository extends JpaRepository<Lote, Long> {
    List<Lote> findByProdutoId(Long produtoId);

    @Query("SELECT l FROM Lote l WHERE l.dataEntrada >= :inicio AND l.dataEntrada <= :fim")
    List<Lote> findByPeriodo(LocalDate inicio, LocalDate fim);
}
