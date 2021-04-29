package com.ratacheski.apiprova.domain.service;

import com.ratacheski.apiprova.domain.model.Cliente;
import com.ratacheski.apiprova.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ClienteService {


    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente create(Cliente cliente) {
        if (clienteRepository.existsById(cliente.getCodigo()))
            throw new EntityExistsException("Cliente já cadastrado com o código " + cliente.getCodigo());
        return clienteRepository.save(cliente);
    }

    public Cliente findById(Integer codigo) {
        return clienteRepository.findById(codigo)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com o código "+codigo));
    }

    public List<Cliente> list() {
        return clienteRepository.findAll();
    }

    public Cliente update (Integer codigo, Cliente cliente) {
        var clienteById = findById(codigo);
        cliente.setCodigo(clienteById.getCodigo());
        return clienteRepository.save(cliente);
    }

    public void delete (Integer codigo) {
        var cliente = findById(codigo);
        clienteRepository.delete(cliente);
    }
}
