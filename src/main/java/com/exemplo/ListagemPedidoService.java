package com.exemplo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

import com.exemplo.ListagemPedidoRepository; // Import adicionado

@Service
public class ListagemPedidoService {

    private static final Logger logger = LoggerFactory.getLogger(ListagemPedidoService.class);

    @Autowired
    private ListagemPedidoRepository listagemPedidoRepository;

    public List<ListagemPedido> listarPorPeriodo(Timestamp dataInicio, Timestamp dataFim) {
        logger.info("Listando pedidos para o período: dataInicio={}, dataFim={}", dataInicio, dataFim);
        List<ListagemPedido> pedidos = listagemPedidoRepository.findByPeriodo(dataInicio, dataFim);
        logger.info("Encontrados {} pedidos.", pedidos.size());
        return pedidos;
    }
}