package com.ratacheski.apiprova.domain.service;

import com.ratacheski.apiprova.domain.model.Cliente;
import com.ratacheski.apiprova.domain.model.Produto;
import com.ratacheski.apiprova.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public List<Cliente> list(String filter, String sort, int page, int size) {
        var sortByCodigo = Sort.by("codigo");
        if (sort.equals("desc")) sortByCodigo = sortByCodigo.descending();
        Pageable pageable = PageRequest.of(page, size, sortByCodigo);
        if (filter == null || filter.isBlank())
            return clienteRepository.findAll(pageable).getContent();
        else
            filter = filter.trim();
        return clienteRepository.findAllByNomeIgnoreCaseContaining(filter,pageable).getContent();
    }

    public long count() {
        return clienteRepository.count();
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
