package com.ryan.estudos_spring.controller;

import com.ryan.estudos_spring.model.Produto;
import com.ryan.estudos_spring.repository.ProdutoRepository;
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
    public List<Produto> getProdutos() {
        return produtoRepository.findAll();
    }

    @GetMapping("/produtos/{id}")
    public Optional<Produto> buscarPorId(@PathVariable Long id){
        if(produtoRepository.findById(id) != null){
            return produtoRepository.findById(id);
        }
        return null;
    }
    @PostMapping("/produtos")
    public Produto criarProduto(@RequestBody Produto produto){
        if(produto != null){
            return produtoRepository.save(produto);
        }
        return null;
    }
    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Void> apagarProduto(@PathVariable Long id){
        if(id != null){
            produtoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
