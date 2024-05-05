package br.com.fiap.gerenciadorDepedidos.produtos.useCases.cargaDeProdutos;

import br.com.fiap.gerenciadorDepedidos.produtos.adapters.ProdutoAdapter;
import br.com.fiap.gerenciadorDepedidos.produtos.entities.ProdutoEntity;
import br.com.fiap.gerenciadorDepedidos.produtos.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ImportarProdutosCsvAgendadoUseCase {

    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    ProdutoAdapter produtoAdapter;

    @Async
    public void importarProdutosCsvAgendado(File file) {

        try {
            List<ProdutoEntity> produtos = produtoAdapter.processarCsvAgendado(file);
            produtoRepository.saveAll(produtos);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
