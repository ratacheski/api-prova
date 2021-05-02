package com.ratacheski.apiprova.domain.repository;

import com.ratacheski.apiprova.domain.model.Cliente;
import com.ratacheski.apiprova.domain.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
    Page<Cliente> findAllByNomeIgnoreCaseContaining(String nome, Pageable pageable);
}
