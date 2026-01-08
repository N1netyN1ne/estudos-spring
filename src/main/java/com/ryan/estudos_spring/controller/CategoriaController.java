package com.ryan.estudos_spring.controller;

import com.ryan.estudos_spring.model.Categoria;
import com.ryan.estudos_spring.repository.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoriaController {
    private CategoriaRepository categoriaRepository;

    CategoriaController(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<Categoria>> ListarCategorias(){
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    @PostMapping("/categoria")
    public ResponseEntity<Categoria> criarCategoria(@Valid @RequestBody Categoria categoria){
        if(categoria != null){
            ResponseEntity.status(201).body(categoriaRepository.save(categoria));
        }return ResponseEntity.badRequest().build();
    }
}
