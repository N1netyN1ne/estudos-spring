package com.ryan.estudos_spring.controller;

import com.ryan.estudos_spring.exception.CategoriaNaoEncontradaException;
import com.ryan.estudos_spring.model.Categoria;
import com.ryan.estudos_spring.repository.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> ListarCategorias(){
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<Categoria> buscarCategoria(@PathVariable Long id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if(categoria.isPresent()){
            return  ResponseEntity.ok(categoria.get());
        } throw new CategoriaNaoEncontradaException("Categoria id " + id + " não encontrado");
    }

    @PostMapping("/categorias")
    public ResponseEntity<Categoria> criarCategoria(@Valid @RequestBody Categoria categoria){
        if(categoria != null){
            return ResponseEntity.status(201).body(categoriaRepository.save(categoria));
        }return ResponseEntity.badRequest().build();

    }
}
