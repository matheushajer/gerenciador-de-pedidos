package br.com.fiap.gerenciadorDepedidos.entrega.controllers;

import br.com.fiap.gerenciadorDepedidos.entrega.records.DadosEntregaDTO;
import br.com.fiap.gerenciadorDepedidos.entrega.records.EntregaDTO;
import br.com.fiap.gerenciadorDepedidos.entrega.records.RequestCEPDTO;
import br.com.fiap.gerenciadorDepedidos.entrega.useCases.BuscarPedidoEntrega;
import br.com.fiap.gerenciadorDepedidos.entrega.useCases.CriarPedidoEntrega;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Classe Controller Entregas.
 */
@RestController
@RequestMapping("/entrega")
public class EntregaController {
    @Autowired
    CriarPedidoEntrega criarPedidoEntrega;
    @Autowired
    BuscarPedidoEntrega buscarPedidoEntrega;

    @PostMapping("/buscar-entrega/{pedido}")
    public ResponseEntity<EntregaDTO> statusPedido(@PathVariable Long pedido) throws Exception {

        return (ResponseEntity<EntregaDTO>) ResponseEntity.ok(buscarPedidoEntrega.buscarPedido(pedido));

    }

    @PostMapping("/criar-entrega")
    public ResponseEntity<DadosEntregaDTO> criarEntrega(@RequestBody @Validated RequestCEPDTO requestCEPDTO) throws Exception {
        return (ResponseEntity<DadosEntregaDTO>) ResponseEntity.ok(criarPedidoEntrega.criarEntrega(requestCEPDTO));
    }
}