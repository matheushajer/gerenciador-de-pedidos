package br.com.fiap.gerenciadorDepedidos.clientes.repositories;

import br.com.fiap.gerenciadorDepedidos.clientes.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    ClienteEntity findByCpf(String cpf);

    boolean existsByCpf(String cpf);
}
