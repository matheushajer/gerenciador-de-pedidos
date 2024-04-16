package br.com.fiap.gerenciadorDepedidos.clientes.controllers.endereco;

import br.com.fiap.gerenciadorDepedidos.clientes.records.endereco.DadosAtualizarEnderecoDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.useCases.endereço.CriarEnderecoClienteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final CriarEnderecoClienteUseCase criarEnderecoClienteUseCase;

    @PostMapping("{clientId}/adicionar-endereco")
    public ResponseEntity<String> criarEnderecoCliente(@PathVariable Long clientId, @RequestBody List<DadosAtualizarEnderecoDTO> atualizarEnderecos) {
        return ResponseEntity.ok(criarEnderecoClienteUseCase.criarEnderecoCliente(clientId, atualizarEnderecos));
    }
}