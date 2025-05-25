package com.exemplo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listar() {
        try {
            List<Produto> produtos = produtoRepository.findAll();
            System.out.println("Produtos carregados pelo ProdutoService: " + produtos.size());
            if (produtos.isEmpty()) {
                System.out.println("Nenhum produto encontrado no banco de dados. Verifique os dados disponíveis e a configuração do DataSource.");
            } else {
                System.out.println("Produtos encontrados: " + produtos);
            }
            return produtos;
        } catch (Exception e) {
            System.out.println("Erro ao carregar produtos: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }

    public void save(Produto produto) {
        try {
            produtoRepository.save(produto);
        } catch (Exception e) {
            System.out.println("Erro ao salvar produto: " + e.getMessage());
            throw new RuntimeException("Falha ao salvar produto", e);
        }
    }

    public Optional<Produto> findById(Integer id) {
        return produtoRepository.findById(id);
    }

    public void delete(Produto produto) {
        produtoRepository.delete(produto);
    }

    public void salvarProduto(Produto produto) {
        save(produto);
    }

    public Optional<Produto> buscar(Integer id) {
        return findById(id);
    }

    public void excluir(Produto produto) {
        delete(produto);
    }
}