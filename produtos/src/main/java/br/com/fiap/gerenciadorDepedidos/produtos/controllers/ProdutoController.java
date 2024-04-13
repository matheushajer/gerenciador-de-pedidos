package br.com.fiap.gerenciadorDepedidos.produtos.controllers;

import br.com.fiap.gerenciadorDepedidos.produtos.records.DadosCriacaoProdutoDTO;
import br.com.fiap.gerenciadorDepedidos.produtos.useCases.CriarProdutoUseCase;
import br.com.fiap.gerenciadorDepedidos.produtos.useCases.DeletarProdutoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Classe Controller das operações do Produto.
 */
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    CriarProdutoUseCase criarProdutoUseCase;
    @Autowired
    DeletarProdutoUseCase deletarProdutoUseCase;

    @PostMapping("/criar-produto")
    public ResponseEntity<DadosCriacaoProdutoDTO> criarProduto(
            @RequestBody @Validated DadosCriacaoProdutoDTO dadosCriacaoProdutoDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(criarProdutoUseCase.criarProduto(dadosCriacaoProdutoDTO));

    }

    @DeleteMapping("/{produto_id}")
    public void deletarProduto(@PathVariable Long produto_id) {

        deletarProdutoUseCase.deletarProduto(produto_id);

    }


}
