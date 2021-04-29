package com.ratacheski.apiprova.api.model.input;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ProdutoInput {
    @NotNull
    @Min(0)
    private Integer codigo;

    @NotBlank
    @Size(max = 255)
    private String nome;
}
