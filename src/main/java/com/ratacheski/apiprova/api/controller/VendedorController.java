package com.ratacheski.apiprova.api.controller;

import com.ratacheski.apiprova.api.model.input.VendedorInput;
import com.ratacheski.apiprova.api.model.output.ClienteListOutput;
import com.ratacheski.apiprova.api.model.output.ClienteOutput;
import com.ratacheski.apiprova.api.model.output.VendedorListOutput;
import com.ratacheski.apiprova.api.model.output.VendedorOutput;
import com.ratacheski.apiprova.domain.model.Vendedor;
import com.ratacheski.apiprova.domain.service.VendedorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping(path = "/vendedores")
public class VendedorController {
    @Autowired
    private VendedorService vendedorService;
    @Autowired
    private ModelMapper modelMapper;


    @GetMapping
    public VendedorListOutput list(@RequestParam(required = false) String filter,
                                   @RequestParam(required = false, defaultValue = "asc") String sort,
                                   @RequestParam(required = false, defaultValue = "0") int page,
                                   @RequestParam(required = false, defaultValue = "10") int size) {
        var vendedores = vendedorService.list(filter, sort, page, size);
        var list = vendedores.stream()
                .map(vendedor ->
                        modelMapper.map(vendedor, VendedorOutput.class)
                ).collect(Collectors.toList());
        var count = vendedorService.count();
        return new VendedorListOutput(count,list);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public VendedorOutput create(@Valid @RequestBody VendedorInput vendedorInput) {
        var vendedor = vendedorService.create(modelMapper.map(vendedorInput, Vendedor.class));
        return modelMapper.map(vendedor, VendedorOutput.class);
    }

    @PutMapping(path = "/{codigo}")
    public VendedorOutput update(@Valid @RequestBody VendedorInput vendedorInput, @PathVariable Integer codigo) {
        var vendedor = modelMapper.map(vendedorInput, Vendedor.class);
        var updatedVendedor = vendedorService.update(codigo, vendedor);
        return modelMapper.map(updatedVendedor, VendedorOutput.class);
    }

    @DeleteMapping(path = "/{codigo}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer codigo) {
        vendedorService.delete(codigo);
    }

    @GetMapping(path = "/{codigo}")
    public VendedorOutput findById(@PathVariable Integer codigo) {
        var vendedor = vendedorService.findById(codigo);
        return modelMapper.map(vendedor, VendedorOutput.class);
    }
}
