package com.ratacheski.apiprova.domain.repository;

import com.ratacheski.apiprova.domain.model.Produto;
import com.ratacheski.apiprova.domain.model.Vendedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor,Integer> {
    Page<Vendedor> findAllByNomeIgnoreCaseContaining(String nome, Pageable pageable);
}
