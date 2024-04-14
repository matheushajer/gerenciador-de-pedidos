package br.com.fiap.gerenciadorDepedidos.produtos.controllers;

import br.com.fiap.gerenciadorDepedidos.produtos.entities.ProdutoEntity;
import br.com.fiap.gerenciadorDepedidos.produtos.records.DadosAtualizacaoProdutoDTO;
import br.com.fiap.gerenciadorDepedidos.produtos.records.DadosCriacaoProdutoDTO;
import br.com.fiap.gerenciadorDepedidos.produtos.useCases.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Autowired
    AtualizarDadosProdutoUseCase atualizarDadosProdutoUseCase;
    @Autowired
    ListarTodosProdutosPaginadoUseCase listarTodosProdutosPaginado;
    @Autowired
    BuscarProdutoPorIdUseCase buscarProdutoPorIdUseCase;

    @GetMapping
    public Page<ProdutoEntity> listarTodosProdutosPaginado(Pageable pageable) {

        return listarTodosProdutosPaginado.listarTodosProdutosPaginado(pageable);

    }

    @GetMapping("/{produto_id}")
    public ResponseEntity<ProdutoEntity> buscarProdutoPorId(@PathVariable Long produto_id) {
        return ResponseEntity.ok(buscarProdutoPorIdUseCase.buscarProdutoPorId(produto_id));
    }

    @PostMapping("/criar-produto")
    public ResponseEntity<DadosCriacaoProdutoDTO> criarProduto(
            @RequestBody @Validated DadosCriacaoProdutoDTO dadosCriacaoProdutoDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(criarProdutoUseCase.criarProduto(dadosCriacaoProdutoDTO));

    }

    @DeleteMapping("/{produto_id}")
    public void deletarProduto(@PathVariable Long produto_id) {

        deletarProdutoUseCase.deletarProduto(produto_id);

    }

    @PatchMapping("/atualizar-produto/{produto_id}")
    public ResponseEntity<ProdutoEntity> atualizaDadosProduto(
            @PathVariable Long produto_id, @RequestBody @Validated DadosAtualizacaoProdutoDTO dadosAtualizacaoProdutoDTO) {

        return ResponseEntity.ok(atualizarDadosProdutoUseCase.atualizarDadosProduto(produto_id, dadosAtualizacaoProdutoDTO));

    }


}
