package com.estoque.controller;

import com.estoque.model.Lote;
import com.estoque.model.Movimentacao;
import com.estoque.repository.LoteRepository;
import com.estoque.repository.MovimentacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/relatorios")
@RequiredArgsConstructor
public class RelatorioController {

    private final MovimentacaoRepository movimentacaoRepository;
    private final LoteRepository loteRepository;

    @GetMapping("/movimentacoes")
    public List<Movimentacao> movimentacoesMes(
            @RequestParam(defaultValue = "0") int ano,
            @RequestParam(defaultValue = "0") int mes) {

        YearMonth ym = (ano == 0 || mes == 0) ? YearMonth.now() : YearMonth.of(ano, mes);
        LocalDate inicio = ym.atDay(1);
        LocalDate fim = ym.atEndOfMonth();
        return movimentacaoRepository.findByPeriodo(inicio, fim);
    }

    @GetMapping("/lotes")
    public List<Lote> lotesMes(
            @RequestParam(defaultValue = "0") int ano,
            @RequestParam(defaultValue = "0") int mes) {

        YearMonth ym = (ano == 0 || mes == 0) ? YearMonth.now() : YearMonth.of(ano, mes);
        LocalDate inicio = ym.atDay(1);
        LocalDate fim = ym.atEndOfMonth();
        return loteRepository.findByPeriodo(inicio, fim);
    }
}
