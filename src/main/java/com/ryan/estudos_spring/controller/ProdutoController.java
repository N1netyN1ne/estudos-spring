package com.ryan.estudos_spring.controller;

import com.ryan.estudos_spring.model.Produto;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class ProdutoController {
    private List<Produto> produtos = new ArrayList<Produto>();
    ProdutoController(){
        produtos.add(new Produto(1L,"Banana",5));
        produtos.add(new Produto(2L,"Maçã",7));
        produtos.add(new Produto(3L,"Pera",3));
    }
    @GetMapping("/produtos")
    public List<Produto> getProdutos() {
        return produtos;
    }

    @GetMapping("/produtos/{id}")
    public Produto buscarPorId(@PathVariable Long id){
        for(Produto p:produtos){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }
    @PostMapping("/produtos")
    public Produto criarProduto(@RequestBody Produto produto){
        if(produto != null){
            Long novoId = 1L;
            if(!produtos.isEmpty()){
                Produto ultimo = produtos.get(produtos.size()-1);
                novoId = ultimo.getId()+1;
            }
            produto.setId(novoId);
            produtos.add(produto);
            return produto;
        }
        return null;
    }
    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Void> apagarProduto(@PathVariable Long id){
        boolean removido = false;
        for(Produto produto:produtos){
            if(produto.getId()==id){
                removido = produtos.remove(produto);
            }
        }
        if(removido){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
