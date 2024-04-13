package br.com.fiap.gerenciadorDepedidos.produtos.controllers;

import br.com.fiap.gerenciadorDepedidos.produtos.records.DadosCriacaoProdutoDTO;
import br.com.fiap.gerenciadorDepedidos.produtos.useCases.produto.CriarProdutoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe Controller das operações do Produto.
 */
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    CriarProdutoUseCase criarProdutoUseCase;

    @PostMapping("/criar-produto")
    public ResponseEntity<DadosCriacaoProdutoDTO> criarProduto(
            @RequestBody @Validated DadosCriacaoProdutoDTO dadosCriacaoProdutoDTO) {

        return ResponseEntity.ok(criarProdutoUseCase.criarProduto(dadosCriacaoProdutoDTO));

    }

}
