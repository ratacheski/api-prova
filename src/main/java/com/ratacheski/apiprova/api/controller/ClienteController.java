package com.ratacheski.apiprova.api.controller;

import com.ratacheski.apiprova.api.model.input.ClienteInput;
import com.ratacheski.apiprova.api.model.output.ClienteListOutput;
import com.ratacheski.apiprova.api.model.output.ClienteOutput;
import com.ratacheski.apiprova.api.model.output.ProdutoListOutput;
import com.ratacheski.apiprova.api.model.output.ProdutoOutput;
import com.ratacheski.apiprova.domain.model.Cliente;
import com.ratacheski.apiprova.domain.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/clientes")
public class ClienteController {


    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ModelMapper modelMapper;


    @GetMapping
    public ClienteListOutput list(@RequestParam(required = false) String filter,
                                  @RequestParam(required = false, defaultValue = "asc") String sort,
                                  @RequestParam(required = false, defaultValue = "0") int page,
                                  @RequestParam(required = false, defaultValue = "10") int size) {
        var clientes = clienteService.list(filter, sort, page, size);
        var list = clientes.stream()
                .map(cliente ->
                        modelMapper.map(cliente, ClienteOutput.class)
                ).collect(Collectors.toList());
        var count = clienteService.count();
        return new ClienteListOutput(count,list);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ClienteOutput create (@Valid @RequestBody ClienteInput clienteInput) {
        var cliente = clienteService.create(modelMapper.map(clienteInput, Cliente.class));
        return modelMapper.map(cliente,ClienteOutput.class);
    }

    @PutMapping(path = "/{codigo}")
    public ClienteOutput update (@Valid @RequestBody ClienteInput clienteInput, @PathVariable Integer codigo) {
        var cliente = modelMapper.map(clienteInput, Cliente.class);
        var updatedCliente = clienteService.update(codigo, cliente);
        return modelMapper.map(updatedCliente,ClienteOutput.class);
    }

    @DeleteMapping(path = "/{codigo}")
    @ResponseStatus(NO_CONTENT)
    public void delete (@PathVariable Integer codigo) {
        clienteService.delete(codigo);
    }

    @GetMapping(path = "/{codigo}")
    public ClienteOutput findById(@PathVariable Integer codigo) {
        var cliente = clienteService.findById(codigo);
        return modelMapper.map(cliente, ClienteOutput.class);
    }
}
