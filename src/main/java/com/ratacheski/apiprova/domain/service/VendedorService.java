package com.ratacheski.apiprova.domain.service;

import com.ratacheski.apiprova.domain.model.Vendedor;
import com.ratacheski.apiprova.domain.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    public Vendedor create(Vendedor vendedor) {
        if (vendedorRepository.existsById(vendedor.getCodigo()))
            throw new EntityExistsException("Vendedor já cadastrado com o código " + vendedor.getCodigo());
        return vendedorRepository.save(vendedor);
    }

    public Vendedor findById(Integer codigo) {
        return vendedorRepository.findById(codigo)
                .orElseThrow(() -> new EntityNotFoundException("Vendedor não encontrado com o código " + codigo));
    }

    public List<Vendedor> list() {
        return vendedorRepository.findAll();
    }

    public Vendedor update(Integer codigo, Vendedor vendedor) {
        var vendedorById = findById(codigo);
        vendedor.setCodigo(vendedorById.getCodigo());
        return vendedorRepository.save(vendedor);
    }

    public void delete(Integer codigo) {
        var vendedor = findById(codigo);
        vendedorRepository.delete(vendedor);
    }
}
