package br.com.fiap.gerenciadorDepedidos.clientes.controllers.cliente;

import br.com.fiap.gerenciadorDepedidos.clientes.records.cliente.DadosCriacaoClienteDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.useCases.cliente.CriarClienteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe Controller das operações do Restaurante
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    CriarClienteUseCase criarClienteUseCase;

    @PostMapping("/criar-cliente")
    public ResponseEntity<DadosCriacaoClienteDTO> criarCiente(
            @RequestBody @Validated DadosCriacaoClienteDTO dadosCriacaoClienteDTO) {

        return ResponseEntity.ok(criarClienteUseCase.criarCliente(dadosCriacaoClienteDTO));

    }

}
