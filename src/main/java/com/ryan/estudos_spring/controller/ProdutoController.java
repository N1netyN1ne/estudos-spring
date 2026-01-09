package com.ryan.estudos_spring.controller;

import com.ryan.estudos_spring.exception.CategoriaNaoEncontradaException;
import com.ryan.estudos_spring.exception.ProdutoNaoEncontradoException;
import com.ryan.estudos_spring.model.Produto;
import com.ryan.estudos_spring.repository.CategoriaRepository;
import com.ryan.estudos_spring.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProdutoController {
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    CategoriaRepository categoriaRepository;

    @GetMapping("/produtos")
    public ResponseEntity<List<Produto>> getProdutos() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) throws ProdutoNaoEncontradoException {
        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isPresent()) {
            return ResponseEntity.ok(produto.get());
        }

        throw new ProdutoNaoEncontradoException("Produto id " + id + " não encontrado");
    }
    @PostMapping("/produtos")
    public ResponseEntity<Produto> criarProduto(@Valid @RequestBody Produto produto){
        Long idCategoriaProduto = produto.getCategoria().getId();

        if(produto != null){
            if(categoriaRepository.findById(idCategoriaProduto).isPresent()){
                return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
            }
                throw new CategoriaNaoEncontradaException("Categoria id " + idCategoriaProduto + " não encontrada");
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity apagarProdutoPorId(@PathVariable Long id){
        if(id != null && id >= 0 && produtoRepository.existsById(id)){
            produtoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        throw new ProdutoNaoEncontradoException("Produto id " + id + " não encontrado");
    }


}
