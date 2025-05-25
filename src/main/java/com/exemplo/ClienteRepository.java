package com.exemplo;

import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends GenericRepository<Cliente, ClienteId> {
}