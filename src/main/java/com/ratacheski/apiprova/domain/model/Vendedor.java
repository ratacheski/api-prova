package com.ratacheski.apiprova.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Vendedor {
    @Id
    private Integer codigo;
    private String nome;
}
