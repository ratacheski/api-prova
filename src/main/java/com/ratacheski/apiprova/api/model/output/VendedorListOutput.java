package com.ratacheski.apiprova.api.model.output;

import lombok.Data;

import java.util.List;

@Data
public class VendedorListOutput {
    final long count;
    final List<VendedorOutput> data;
}
