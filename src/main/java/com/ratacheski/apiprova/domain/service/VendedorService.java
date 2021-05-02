package com.ratacheski.apiprova.domain.service;

import com.ratacheski.apiprova.domain.model.Cliente;
import com.ratacheski.apiprova.domain.model.Vendedor;
import com.ratacheski.apiprova.domain.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<Vendedor> list(String filter, String sort, int page, int size) {
        var sortByCodigo = Sort.by("codigo");
        if (sort.equals("desc")) sortByCodigo = sortByCodigo.descending();
        Pageable pageable = PageRequest.of(page, size, sortByCodigo);
        if (filter == null || filter.isBlank())
            return vendedorRepository.findAll(pageable).getContent();
        else
            filter = filter.trim();
        return vendedorRepository.findAllByNomeIgnoreCaseContaining(filter,pageable).getContent();
    }

    public long count() {
        return vendedorRepository.count();
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
