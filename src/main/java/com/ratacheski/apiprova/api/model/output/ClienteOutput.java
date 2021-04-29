package com.ratacheski.apiprova.api.model.output;

import lombok.Data;

@Data
public class ClienteOutput {
    private Integer codigo;
    private String nome;
    private VendedorOutput vendedor;
}
