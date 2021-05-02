package com.ratacheski.apiprova.domain.repository;

import com.ratacheski.apiprova.domain.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer> {
    Page<Produto> findAllByNomeIgnoreCaseContaining(String nome, Pageable pageable);
}
