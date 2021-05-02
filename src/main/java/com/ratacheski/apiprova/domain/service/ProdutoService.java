package com.ratacheski.apiprova.domain.service;

import com.ratacheski.apiprova.domain.model.Produto;
import com.ratacheski.apiprova.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public List<Produto> list(String filter, String sort, int page, int size) {
        var sortByCodigo = Sort.by("codigo");
        if (sort.equals("desc")) sortByCodigo = sortByCodigo.descending();
        Pageable pageable = PageRequest.of(page, size, sortByCodigo);
        if (filter == null || filter.isBlank())
            return produtoRepository.findAll(pageable).getContent();
        else
            filter = filter.trim();
            return produtoRepository.findAllByNomeIgnoreCaseContaining(filter,pageable).getContent();
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

    public long count() {
        return produtoRepository.count();
    }
}
