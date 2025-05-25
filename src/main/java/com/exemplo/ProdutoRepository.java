package com.exemplo;

import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends GenericRepository<Produto, Integer> {
}