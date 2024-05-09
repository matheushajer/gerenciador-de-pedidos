package br.com.fiap.gerenciadorDepedidos.pedidos.controllers;

import br.com.fiap.gerenciadorDepedidos.pedidos.records.*;
import br.com.fiap.gerenciadorDepedidos.pedidos.useCases.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Classe Controller para gerenciar as operações de pedidos.
 * Prove endpoints para criação, atualização e gerenciamento de itens de pedidos.
 */
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    CriarPedidoUseCase criarPedidoUseCase;
    @Autowired
    AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;
    @Autowired
    IncluirItemPedidoUseCase incluirItemPedidoUseCase;
    @Autowired
    InserirDadosEntregaUseCase inserirDadosEntregaUseCase;
    @Autowired
    RemoverItemPedidoUseCase removerItemPedidoUseCase;

    /**
     * Endpoint para criar um novo pedido.
     * Recebe um DTO com os dados do pedido e retorna o pedido criado com status HTTP 201.
     */
    @PostMapping("/criar-pedido")
    public ResponseEntity<DadosRetornoCriacaoPedidoDTO> criarPedido(@RequestBody DadosCriacaoPedidoDTO dadosCriacaoPedido) {

        return ResponseEntity.ok(criarPedidoUseCase.criarPedido(dadosCriacaoPedido));

    }

    /**
     * Endpoint para atualizar o status de um pedido existente.
     * Recebe o ID do pedido e um DTO com o novo status, retornando o pedido atualizado.
     */
    @PutMapping("/atualizar-status-pedido/{pedidoId}")
    public ResponseEntity<DadosAtualizacaoStatusPedidoDTO> atualizarStatusPedido(
            @PathVariable Long pedidoId,
            @RequestBody @Validated DadosAtualizacaoStatusPedidoDTO dadosAtualizacaoStatusPedidoDTO) {

        DadosAtualizacaoStatusPedidoDTO atualizadoDTO = atualizarStatusPedidoUseCase.atualizarStatus(pedidoId, dadosAtualizacaoStatusPedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(atualizadoDTO);
    }

    /**
     * Endpoint para incluir itens em um pedido existente.
     * Recebe o ID do pedido e um DTO com os itens a serem incluídos.
     */
    @PostMapping("/incluir-item/{pedidoId}")
    public ResponseEntity<Void> incluirItemAoPedido(
            @PathVariable Long pedidoId,
            @RequestBody @Validated DadosInclusaoItemPedidoDTO dadosInclusaoItemDTO) {
        dadosInclusaoItemDTO = new DadosInclusaoItemPedidoDTO(pedidoId, dadosInclusaoItemDTO.novosItens()); // Garante que o pedidoId do path é usado
        incluirItemPedidoUseCase.incluirItem(dadosInclusaoItemDTO);
        return ResponseEntity.ok().build(); // Retorna 200 OK sem corpo
    }

    /**
     * Endpoint para remover itens de um pedido existente.
     * Recebe o ID do pedido e um DTO com o ID do produto e a quantidade a ser removida.
     */
    @DeleteMapping("/remover-item/{pedidoId}")
    public ResponseEntity<Void> removerItemDoPedido(
            @PathVariable Long pedidoId,
            @RequestBody @Validated DadosRemocaoItemPedidoDTO dadosRemocaoItemDTO) {
        removerItemPedidoUseCase.removerItem(pedidoId, dadosRemocaoItemDTO.produtoId(), dadosRemocaoItemDTO.quantidade());
        return ResponseEntity.ok().build(); // Retorna 200 OK sem corpo
    }

    /**
     * Endpoint para inserir dados de entrega em um pedido existente.
     * Recebe o ID do pedido e um DTO com os dados de entrega a serem atualizados.
     */
//    @PostMapping("/inserir-dados-entrega/{pedidoId}")
//    public ResponseEntity<Void> inserirDadosEntrega(
//            @PathVariable Long pedidoId,
//            @RequestBody @Validated DadosInsercaoDadosEntregaDTO dadosEntregaDTO) {
//        // Passa os valores do DTO como argumentos separados para o caso de uso
//        inserirDadosEntregaUseCase.inserirDadosEntrega(
//                pedidoId,
//                dadosEntregaDTO.prazoDeEntrega(),
//                dadosEntregaDTO.frete(),
//                dadosEntregaDTO.codigoDeRastreio()
//        );
//        return ResponseEntity.ok().build();
//    }

}
