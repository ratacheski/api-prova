package com.ratacheski.apiprova.domain.service;

import com.ratacheski.apiprova.domain.model.Produto;
import com.ratacheski.apiprova.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto create(Produto produto) {
        if (produtoRepository.existsById(produto.getCodigo()))
            throw new EntityExistsException("Produto já cadastrado com o código " + produto.getCodigo());
        return produtoRepository.save(produto);
    }

    public Produto findById(Integer codigo) {
        return produtoRepository.findById(codigo)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o código " + codigo));
    }

    public List<Produto> list() {
        return produtoRepository.findAll();
    }

    public Produto update(Integer codigo, Produto produto) {
        var produtoById = findById(codigo);
        produto.setCodigo(produtoById.getCodigo());
        return produtoRepository.save(produto);
    }

    public void delete(Integer codigo) {
        var produto = findById(codigo);
        produtoRepository.delete(produto);
    }
}
