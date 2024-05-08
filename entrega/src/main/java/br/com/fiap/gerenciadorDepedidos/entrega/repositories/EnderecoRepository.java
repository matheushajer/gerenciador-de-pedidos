package br.com.fiap.gerenciadorDepedidos.entrega.repositories;

import br.com.fiap.gerenciadorDepedidos.entrega.entities.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
    EnderecoEntity findByClienteEntityIdAndIsEnderecoPrincipalIsTrue(Long clienteId);
}