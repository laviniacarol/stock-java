package com.estoque.controller;

import com.estoque.dto.LoteRequest;
import com.estoque.model.Lote;
import com.estoque.model.Movimentacao;
import com.estoque.model.Produto;
import com.estoque.repository.LoteRepository;
import com.estoque.repository.MovimentacaoRepository;
import com.estoque.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lotes")
@RequiredArgsConstructor
public class LoteController {

    private final LoteRepository loteRepository;
    private final ProdutoRepository produtoRepository;
    private final MovimentacaoRepository movimentacaoRepository;

    @GetMapping
    public List<Lote> listar() {
        return loteRepository.findAll();
    }

    @GetMapping("/produto/{produtoId}")
    public List<Lote> listarPorProduto(@PathVariable Long produtoId) {
        return loteRepository.findByProdutoId(produtoId);
    }

    @PostMapping
    public ResponseEntity<?> registrarEntrada(@RequestBody LoteRequest request) {
        Produto produto = produtoRepository.findById(request.getProdutoId())
                .orElse(null);
        if (produto == null) return ResponseEntity.badRequest().body("Produto não encontrado");

        Lote lote = new Lote();
        lote.setProduto(produto);
        lote.setQuantidade(request.getQuantidade());
        lote.setPrecoCompra(request.getPrecoCompra());
        lote.setDataValidade(request.getDataValidade());
        lote.setFornecedor(request.getFornecedor());
        loteRepository.save(lote);

        // Atualiza estoque
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + request.getQuantidade());
        produtoRepository.save(produto);

        // Registra movimentação
        Movimentacao mov = new Movimentacao();
        mov.setProduto(produto);
        mov.setTipo("ENTRADA");
        mov.setQuantidade(request.getQuantidade());
        mov.setObservacao("Lote registrado - Fornecedor: " + request.getFornecedor());
        movimentacaoRepository.save(mov);

        return ResponseEntity.ok(lote);
    }
}
