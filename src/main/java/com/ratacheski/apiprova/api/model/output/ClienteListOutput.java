package com.ratacheski.apiprova.api.model.output;

import lombok.Data;

import java.util.List;

@Data
public class ClienteListOutput {
    final long count;
    final List<ClienteOutput> data;
}
