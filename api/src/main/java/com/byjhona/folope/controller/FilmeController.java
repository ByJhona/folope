package com.byjhona.folope.controller;

import com.byjhona.folope.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
@RequestMapping("/filme")
public class FilmeController {
    @Autowired
    private FilmeService filmeService;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Mono<String>> mostrar(@PathVariable Long id){
        Mono<String> filme = filmeService.buscarPorId(id);
        return ResponseEntity.ok().body(filme);

    }
}
