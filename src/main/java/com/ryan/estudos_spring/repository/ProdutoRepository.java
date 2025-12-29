package com.ryan.estudos_spring.repository;

import com.ryan.estudos_spring.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {

}
