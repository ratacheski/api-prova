package com.ratacheski.apiprova.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Cliente {
    @Id
    private Integer codigo;
    private String nome;
    @ManyToOne(optional = false)
    private Vendedor vendedor;
}
