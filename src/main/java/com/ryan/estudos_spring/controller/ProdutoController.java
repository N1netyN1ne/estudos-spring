package com.ryan.estudos_spring.controller;

import com.ryan.estudos_spring.model.Produto;
import com.ryan.estudos_spring.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProdutoController {
    ProdutoRepository produtoRepository;
    ProdutoController(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/produtos")
    public ResponseEntity<List<Produto>> getProdutos() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<Optional<Produto>> buscarPorId(@PathVariable Long id){
        if(produtoRepository.findById(id) != null){
            return ResponseEntity.ok(produtoRepository.findById(id));
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/produtos")
    public ResponseEntity<Produto> criarProduto(@Valid @RequestBody Produto produto){
        if(produto != null){
            return ResponseEntity.status(201).body(produtoRepository.save(produto));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity apagarProdutoPorId(@PathVariable Long id){
        if(id != null && id >= 0 && produtoRepository.existsById(id)){
            produtoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
