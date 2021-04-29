package com.ratacheski.apiprova.domain.repository;

import com.ratacheski.apiprova.domain.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor,Integer> {
}
