package com.ratacheski.apiprova.api.controller;

import com.ratacheski.apiprova.api.model.input.ProdutoInput;
import com.ratacheski.apiprova.api.model.input.VendedorInput;
import com.ratacheski.apiprova.api.model.output.ProdutoListOutput;
import com.ratacheski.apiprova.api.model.output.ProdutoOutput;
import com.ratacheski.apiprova.domain.model.Produto;
import com.ratacheski.apiprova.domain.model.Vendedor;
import com.ratacheski.apiprova.domain.service.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping(path = "/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ModelMapper modelMapper;


    @GetMapping
    public ProdutoListOutput list(@RequestParam(required = false) String filter,
                                  @RequestParam(required = false, defaultValue = "asc") String sort,
                                  @RequestParam(required = false, defaultValue = "0") int page,
                                  @RequestParam(required = false, defaultValue = "10") int size) {
        var produtos = produtoService.list(filter, sort, page, size);
        var list = produtos.stream()
                .map(produto ->
                        modelMapper.map(produto, ProdutoOutput.class)
                ).collect(Collectors.toList());
        var count = produtoService.count();
        return new ProdutoListOutput(count,list);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ProdutoOutput create(@Valid @RequestBody ProdutoInput produtoInput) {
        var produto = produtoService.create(modelMapper.map(produtoInput, Produto.class));
        return modelMapper.map(produto, ProdutoOutput.class);
    }

    @PutMapping(path = "/{codigo}")
    public ProdutoOutput update(@Valid @RequestBody ProdutoInput produtoInput, @PathVariable Integer codigo) {
        var produto = modelMapper.map(produtoInput, Produto.class);
        var updatedProduto = produtoService.update(codigo, produto);
        return modelMapper.map(updatedProduto, ProdutoOutput.class);
    }

    @DeleteMapping(path = "/{codigo}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer codigo) {
        produtoService.delete(codigo);
    }

    @GetMapping(path = "/{codigo}")
    public ProdutoOutput findById(@PathVariable Integer codigo) {
        var produto = produtoService.findById(codigo);
        return modelMapper.map(produto, ProdutoOutput.class);
    }
}
