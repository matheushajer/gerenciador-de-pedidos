package br.com.fiap.gerenciadorDepedidos.clientes.repositories;

import br.com.fiap.gerenciadorDepedidos.clientes.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> , JpaSpecificationExecutor<ClienteEntity> {
    ClienteEntity findByCpf(String cpf);

    boolean existsByCpf(String cpf);
}
