package com.estoque.controller;

import com.estoque.dto.RetiradaRequest;
import com.estoque.model.Movimentacao;
import com.estoque.model.Produto;
import com.estoque.repository.MovimentacaoRepository;
import com.estoque.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movimentacoes")
@RequiredArgsConstructor
public class MovimentacaoController {

    private final MovimentacaoRepository movimentacaoRepository;
    private final ProdutoRepository produtoRepository;

    @PostMapping("/retirada")
    public ResponseEntity<?> registrarRetirada(@RequestBody RetiradaRequest request) {
        Produto produto = produtoRepository.findById(request.getProdutoId())
                .orElse(null);
        if (produto == null) return ResponseEntity.badRequest().body("Produto não encontrado");

        if (produto.getQuantidadeEstoque() < request.getQuantidade()) {
            return ResponseEntity.badRequest().body("Estoque insuficiente. Disponível: " + produto.getQuantidadeEstoque());
        }

        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - request.getQuantidade());
        produtoRepository.save(produto);

        Movimentacao mov = new Movimentacao();
        mov.setProduto(produto);
        mov.setTipo("SAIDA");
        mov.setQuantidade(request.getQuantidade());
        mov.setObservacao(request.getObservacao());
        movimentacaoRepository.save(mov);

        return ResponseEntity.ok(mov);
    }
}
