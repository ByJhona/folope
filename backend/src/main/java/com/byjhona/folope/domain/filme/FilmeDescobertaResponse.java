package com.byjhona.folope.domain.filme;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;

import java.util.List;


public record FilmeDescobertaResponse(
        Integer pagina,
        Integer quantPaginas,
        List<FilmeDescobertaDTO> filmes
) {
}
