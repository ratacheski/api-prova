package com.ratacheski.apiprova.api.model.output;

import lombok.Data;

import java.util.List;

@Data
public class ProdutoListOutput {
    final long count;
    final List<ProdutoOutput> data;
}
