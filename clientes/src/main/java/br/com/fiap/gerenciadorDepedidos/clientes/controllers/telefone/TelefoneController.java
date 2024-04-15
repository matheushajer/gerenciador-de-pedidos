package br.com.fiap.gerenciadorDepedidos.clientes.controllers.telefone;

import br.com.fiap.gerenciadorDepedidos.clientes.records.telefone.DadosAtualizarTelefoneDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.useCases.telefone.CriarTelefoneClienteUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/telefones")
public class TelefoneController {

    @Autowired
    private CriarTelefoneClienteUseCase criarTelefoneClienteUseCase;

    @PostMapping("{clientId}/adicionar-telefone")
    private ResponseEntity<String> criarTelefoneCliente(@PathVariable Long clientId, @RequestBody @Valid List<DadosAtualizarTelefoneDTO> atualizarTelefones) {
        return ResponseEntity.ok(criarTelefoneClienteUseCase.criarTelefoneCliente(clientId, atualizarTelefones));
    }

}
