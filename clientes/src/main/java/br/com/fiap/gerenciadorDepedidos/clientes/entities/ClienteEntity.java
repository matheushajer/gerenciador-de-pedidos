package br.com.fiap.gerenciadorDepedidos.clientes.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para representar a Entidade Cliente
 */
@Entity
@Data
@Table(name = "tb_cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String email;

    @OneToMany(mappedBy = "clienteEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TelefoneEntity> telefoneEntity;

    @OneToMany(mappedBy = "clienteEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<EnderecoEntity> enderecoEntity;

    // **************
    // Construtores
    // **************

    public ClienteEntity() {
    }

    public ClienteEntity(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

}
